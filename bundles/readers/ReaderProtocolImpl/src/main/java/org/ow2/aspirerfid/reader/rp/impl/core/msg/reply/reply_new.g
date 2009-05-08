header
{ 
	package org.accada.reader.msg.reply;
}

{
	import org.accada.reader.msg.reply.impl.ErrorTypeImpl;
}

class TextReplyParser extends Parser;

options
{
	defaultErrorHandler = false;
}

tokens
{
	OK = "OK";
	ERR = "ERR";
}

{
	TextReplyParserHelper helper = new TextReplyParserHelper();
}
	
reply returns [TextReplyParserHelper parserHelper]
	{
		parserHelper = helper;
	}
	:	(reply_header)? (command_specific_reply)? COMMAND_REPLY_TERMINATOR;
	
reply_header
	{
		String id = null;
	}
	:	(id = command_id
	{
		helper.setId(id);
	}
	)? reply_status LF;

command_id returns [String s]
	{
		int r = 0;
		s = null;
	}
	:	EXCLAMATION r = dec_val
	{
		s = Integer.toString(r);
	};
	
reply_status
	:	(reply_success | reply_error);
	
reply_success
	{
		int statusCode = 0;
		int statusInfo = 0;
	}
	:	OK (COMMA statusCode = status_code
	{
		helper.setStatusCode(statusCode);
	}
	(COMMA status_info)? )?;
	
reply_error
	{
		int statusCode = 0;
		int statusInfo = 0;
	}
	:	ERR COMMA statusCode = status_code
	{
		helper.setStatusCode(statusCode);
	}
	(COMMA status_info)?;
	
status_code returns [int code]
	{
		code = 0;
	}
	:	code = dec_val;
	
status_info
	{
		ErrorType errorType = new ErrorTypeImpl();
		String resultName = null;
		String resultCause = null;
		String resultDescription = null;
	}
	:	(resultName = result_name)? (COMMA (resultCause = result_cause)? (COMMA resultDescription = result_description)? )?
	{
		errorType.setName(resultName);
		errorType.setCause(resultCause);
		errorType.setDescription(resultDescription);
		helper.setErrorType(errorType);
	};
	
result_name returns [String name]
	{
		name = null;
	}
	:	name = string_val;
	
result_cause returns [String cause]
	{
		cause = null;
	}
	:	cause = string_val;
	
result_description returns [String description]
	{
		description = null;
	}
	:	resultDescription:String_val
	{
		description = resultDescription.getText();
	};
	
command_specific_reply
	:	(reply_line)*;

reply_line
	{
		String replyValue = null;
	}
	:	replyValue = reply_value
	{
		helper.addValue(replyValue.trim());
	}
		(COMMA replyValue = reply_value
	{
		helper.addValue(replyValue.trim());
	}
		)* LF;
	
reply_value returns [String value]
	{
		value = null;
		int dec_value = 0;
	}
	:	value = string_val | dec_value = dec_val
	{
		value = Integer.toString(dec_value);
	};
//	:	(dec_val | hex_val | IDENT | reply_list);

dec_val returns [int r]
	{
		r = 0;
	}
	:	i:INT
	{
		r = Integer.parseInt(i.getText());
	};
	
string_val returns [String r]
	{
		r = null;
	}
	:	s:STRING
	{
		r = s.getText();
	};
		
class TextLexer extends Lexer;

options
	{
		caseSensitiveLiterals  = false;
		k=2;
	}
	
WS
	:	(' ' | '\t')
	{
		_ttype = Token.SKIP;
	};
	
LF
	:	(	"\r\n"  // Evil DOS
		|	'\r'    // Macintosh
		|	'\n'    // Unix (the right way)
		)
	{
		newline();
	};
	
SHARP
	:	"#";

DOT
	:	".";

EXCLAMATION
     :	"!";
     
COMMA
	:	",";

LBRACKET
	:	"{";

RBRACKET
	:	"}";

ASSIGN
	:	"=";
	
ESCAPE
	:	"\\";

COMMAND_REPLY_TERMINATOR
	:	">";
	
protected
DIGIT
	:	'0'..'9';

//INT	:	('1'..'9') ('0'..'9')*	
INT
	:	('0'..'9')+;

STRING
	:	(STRINGCHAR1 | (ESCAPE STRINGCHAR2))+;
	
STRINGCHAR1
//	:	(' '..'*'|'-'|'/'..'9'|';'|'<'|'?'..'['|']'..'z'|'|'|'~');
	:	(' '..'*'|'-'..'<'|'?'..'['|']'..'z'|'|'|'~');
	
STRINGCHAR2
	:	(' '..'~');
	
IDENT
	options
	{
		testLiterals=true;
	}
	:	('A'..'Z' | 'a'..'z') ('A'..'Z'|'a'..'z'|'0'..'9')*;