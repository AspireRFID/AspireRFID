
package org.fosstrak.ale.wsdl.alecc.epcglobal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.fosstrak.ale.xsd.ale.epcglobal.AssocTableEntryList;
import org.fosstrak.ale.xsd.ale.epcglobal.AssocTableSpec;
import org.fosstrak.ale.xsd.ale.epcglobal.CCReports;
import org.fosstrak.ale.xsd.ale.epcglobal.CCSpec;
import org.fosstrak.ale.xsd.ale.epcglobal.EPCCacheSpec;
import org.fosstrak.ale.xsd.ale.epcglobal.EPCPatternList;
import org.fosstrak.ale.xsd.ale.epcglobal.RNGSpec;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.fosstrak.ale.wsdl.alecc.epcglobal package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRNGResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetRNGResult");
    private final static QName _GetEPCCache_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetEPCCache");
    private final static QName _ImmediateResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "ImmediateResult");
    private final static QName _DefineAssocTable_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DefineAssocTable");
    private final static QName _GetAssocTableValueResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableValueResult");
    private final static QName _GetRNG_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetRNG");
    private final static QName _GetCCSpecResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetCCSpecResult");
    private final static QName _GetSubscribersResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetSubscribersResult");
    private final static QName _GetAssocTableValue_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableValue");
    private final static QName _AssocTableValidationException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "AssocTableValidationException");
    private final static QName _ReplenishEPCCache_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "ReplenishEPCCache");
    private final static QName _GetVendorVersionResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetVendorVersionResult");
    private final static QName _GetAssocTable_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTable");
    private final static QName _DepleteEPCCacheResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DepleteEPCCacheResult");
    private final static QName _UndefineAssocTable_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "UndefineAssocTable");
    private final static QName _GetRNGNames_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetRNGNames");
    private final static QName _ImplementationException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "ImplementationException");
    private final static QName _DepleteEPCCache_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DepleteEPCCache");
    private final static QName _DuplicateNameException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DuplicateNameException");
    private final static QName _Unsubscribe_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "Unsubscribe");
    private final static QName _PutAssocTableEntries_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "PutAssocTableEntries");
    private final static QName _GetRNGNamesResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetRNGNamesResult");
    private final static QName _GetCCSpecNames_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetCCSpecNames");
    private final static QName _NoSuchNameException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "NoSuchNameException");
    private final static QName _ParameterException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "ParameterException");
    private final static QName _GetSubscribers_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetSubscribers");
    private final static QName _GetAssocTableEntriesResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableEntriesResult");
    private final static QName _InvalidURIException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "InvalidURIException");
    private final static QName _NoSuchSubscriberException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "NoSuchSubscriberException");
    private final static QName _GetEPCCacheNames_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetEPCCacheNames");
    private final static QName _InvalidAssocTableEntryException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "InvalidAssocTableEntryException");
    private final static QName _RemoveAssocTableEntries_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "RemoveAssocTableEntries");
    private final static QName _GetAssocTableNamesResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableNamesResult");
    private final static QName _GetVendorVersion_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetVendorVersion");
    private final static QName _UndefineEPCCache_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "UndefineEPCCache");
    private final static QName _GetAssocTableNames_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableNames");
    private final static QName _GetEPCCacheNamesResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetEPCCacheNamesResult");
    private final static QName _GetStandardVersion_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetStandardVersion");
    private final static QName _Poll_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "Poll");
    private final static QName _UndefineRNG_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "UndefineRNG");
    private final static QName _InUseException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "InUseException");
    private final static QName _GetStandardVersionResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetStandardVersionResult");
    private final static QName _ALEException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "ALEException");
    private final static QName _DefineEPCCache_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DefineEPCCache");
    private final static QName _UndefineEPCCacheResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "UndefineEPCCacheResult");
    private final static QName _GetAssocTableEntries_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableEntries");
    private final static QName _SecurityException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "SecurityException");
    private final static QName _Undefine_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "Undefine");
    private final static QName _RNGValidationException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "RNGValidationException");
    private final static QName _GetEPCCacheContentsResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetEPCCacheContentsResult");
    private final static QName _InvalidPatternException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "InvalidPatternException");
    private final static QName _PollResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "PollResult");
    private final static QName _DefineRNG_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DefineRNG");
    private final static QName _RemoveAssocTableEntry_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "RemoveAssocTableEntry");
    private final static QName _GetAssocTableResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetAssocTableResult");
    private final static QName _GetCCSpec_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetCCSpec");
    private final static QName _CCSpecValidationException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "CCSpecValidationException");
    private final static QName _ParameterForbiddenException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "ParameterForbiddenException");
    private final static QName _GetEPCCacheResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetEPCCacheResult");
    private final static QName _GetEPCCacheContents_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetEPCCacheContents");
    private final static QName _EPCCacheSpecValidationException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "EPCCacheSpecValidationException");
    private final static QName _Subscribe_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "Subscribe");
    private final static QName _GetCCSpecNamesResult_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "GetCCSpecNamesResult");
    private final static QName _DuplicateSubscriptionException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "DuplicateSubscriptionException");
    private final static QName _Immediate_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "Immediate");
    private final static QName _InvalidEPCException_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "InvalidEPCException");
    private final static QName _Define_QNAME = new QName("urn:epcglobal:alecc:wsdl:1", "Define");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.fosstrak.ale.wsdl.alecc.epcglobal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ALEException }
     * 
     */
    public ALEException createALEException() {
        return new ALEException();
    }

    /**
     * Create an instance of {@link Undefine }
     * 
     */
    public Undefine createUndefine() {
        return new Undefine();
    }

    /**
     * Create an instance of {@link DefineResult }
     * 
     */
    public DefineResult createDefineResult() {
        return new DefineResult();
    }

    /**
     * Create an instance of {@link UnsubscribeResult }
     * 
     */
    public UnsubscribeResult createUnsubscribeResult() {
        return new UnsubscribeResult();
    }

    /**
     * Create an instance of {@link DefineAssocTableResult }
     * 
     */
    public DefineAssocTableResult createDefineAssocTableResult() {
        return new DefineAssocTableResult();
    }

    /**
     * Create an instance of {@link AssocTableValidationException }
     * 
     */
    public AssocTableValidationException createAssocTableValidationException() {
        return new AssocTableValidationException();
    }

    /**
     * Create an instance of {@link PutAssocTableEntriesResult }
     * 
     */
    public PutAssocTableEntriesResult createPutAssocTableEntriesResult() {
        return new PutAssocTableEntriesResult();
    }

    /**
     * Create an instance of {@link RemoveAssocTableEntryResult }
     * 
     */
    public RemoveAssocTableEntryResult createRemoveAssocTableEntryResult() {
        return new RemoveAssocTableEntryResult();
    }

    /**
     * Create an instance of {@link InvalidPatternException }
     * 
     */
    public InvalidPatternException createInvalidPatternException() {
        return new InvalidPatternException();
    }

    /**
     * Create an instance of {@link UndefineResult }
     * 
     */
    public UndefineResult createUndefineResult() {
        return new UndefineResult();
    }

    /**
     * Create an instance of {@link Immediate }
     * 
     */
    public Immediate createImmediate() {
        return new Immediate();
    }

    /**
     * Create an instance of {@link GetAssocTableValue }
     * 
     */
    public GetAssocTableValue createGetAssocTableValue() {
        return new GetAssocTableValue();
    }

    /**
     * Create an instance of {@link ImplementationException }
     * 
     */
    public ImplementationException createImplementationException() {
        return new ImplementationException();
    }

    /**
     * Create an instance of {@link UndefineRNG }
     * 
     */
    public UndefineRNG createUndefineRNG() {
        return new UndefineRNG();
    }

    /**
     * Create an instance of {@link GetCCSpec }
     * 
     */
    public GetCCSpec createGetCCSpec() {
        return new GetCCSpec();
    }

    /**
     * Create an instance of {@link InvalidEPCException }
     * 
     */
    public InvalidEPCException createInvalidEPCException() {
        return new InvalidEPCException();
    }

    /**
     * Create an instance of {@link GetEPCCacheContents }
     * 
     */
    public GetEPCCacheContents createGetEPCCacheContents() {
        return new GetEPCCacheContents();
    }

    /**
     * Create an instance of {@link DuplicateSubscriptionException }
     * 
     */
    public DuplicateSubscriptionException createDuplicateSubscriptionException() {
        return new DuplicateSubscriptionException();
    }

    /**
     * Create an instance of {@link NoSuchSubscriberException }
     * 
     */
    public NoSuchSubscriberException createNoSuchSubscriberException() {
        return new NoSuchSubscriberException();
    }

    /**
     * Create an instance of {@link UndefineAssocTable }
     * 
     */
    public UndefineAssocTable createUndefineAssocTable() {
        return new UndefineAssocTable();
    }

    /**
     * Create an instance of {@link CCSpecValidationException }
     * 
     */
    public CCSpecValidationException createCCSpecValidationException() {
        return new CCSpecValidationException();
    }

    /**
     * Create an instance of {@link RemoveAssocTableEntriesResult }
     * 
     */
    public RemoveAssocTableEntriesResult createRemoveAssocTableEntriesResult() {
        return new RemoveAssocTableEntriesResult();
    }

    /**
     * Create an instance of {@link SecurityException }
     * 
     */
    public SecurityException createSecurityException() {
        return new SecurityException();
    }

    /**
     * Create an instance of {@link DefineRNGResult }
     * 
     */
    public DefineRNGResult createDefineRNGResult() {
        return new DefineRNGResult();
    }

    /**
     * Create an instance of {@link UndefineEPCCache }
     * 
     */
    public UndefineEPCCache createUndefineEPCCache() {
        return new UndefineEPCCache();
    }

    /**
     * Create an instance of {@link DefineEPCCache }
     * 
     */
    public DefineEPCCache createDefineEPCCache() {
        return new DefineEPCCache();
    }

    /**
     * Create an instance of {@link GetSubscribers }
     * 
     */
    public GetSubscribers createGetSubscribers() {
        return new GetSubscribers();
    }

    /**
     * Create an instance of {@link GetEPCCache }
     * 
     */
    public GetEPCCache createGetEPCCache() {
        return new GetEPCCache();
    }

    /**
     * Create an instance of {@link RemoveAssocTableEntry }
     * 
     */
    public RemoveAssocTableEntry createRemoveAssocTableEntry() {
        return new RemoveAssocTableEntry();
    }

    /**
     * Create an instance of {@link InvalidAssocTableEntryException }
     * 
     */
    public InvalidAssocTableEntryException createInvalidAssocTableEntryException() {
        return new InvalidAssocTableEntryException();
    }

    /**
     * Create an instance of {@link DefineEPCCacheResult }
     * 
     */
    public DefineEPCCacheResult createDefineEPCCacheResult() {
        return new DefineEPCCacheResult();
    }

    /**
     * Create an instance of {@link UndefineAssocTableResult }
     * 
     */
    public UndefineAssocTableResult createUndefineAssocTableResult() {
        return new UndefineAssocTableResult();
    }

    /**
     * Create an instance of {@link RNGValidationException }
     * 
     */
    public RNGValidationException createRNGValidationException() {
        return new RNGValidationException();
    }

    /**
     * Create an instance of {@link EmptyParms }
     * 
     */
    public EmptyParms createEmptyParms() {
        return new EmptyParms();
    }

    /**
     * Create an instance of {@link Subscribe }
     * 
     */
    public Subscribe createSubscribe() {
        return new Subscribe();
    }

    /**
     * Create an instance of {@link NoSuchNameException }
     * 
     */
    public NoSuchNameException createNoSuchNameException() {
        return new NoSuchNameException();
    }

    /**
     * Create an instance of {@link PutAssocTableEntries }
     * 
     */
    public PutAssocTableEntries createPutAssocTableEntries() {
        return new PutAssocTableEntries();
    }

    /**
     * Create an instance of {@link GetAssocTable }
     * 
     */
    public GetAssocTable createGetAssocTable() {
        return new GetAssocTable();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link ReplenishEPCCache }
     * 
     */
    public ReplenishEPCCache createReplenishEPCCache() {
        return new ReplenishEPCCache();
    }

    /**
     * Create an instance of {@link ParameterException }
     * 
     */
    public ParameterException createParameterException() {
        return new ParameterException();
    }

    /**
     * Create an instance of {@link GetAssocTableEntries }
     * 
     */
    public GetAssocTableEntries createGetAssocTableEntries() {
        return new GetAssocTableEntries();
    }

    /**
     * Create an instance of {@link GetRNG }
     * 
     */
    public GetRNG createGetRNG() {
        return new GetRNG();
    }

    /**
     * Create an instance of {@link ParameterForbiddenException }
     * 
     */
    public ParameterForbiddenException createParameterForbiddenException() {
        return new ParameterForbiddenException();
    }

    /**
     * Create an instance of {@link DuplicateNameException }
     * 
     */
    public DuplicateNameException createDuplicateNameException() {
        return new DuplicateNameException();
    }

    /**
     * Create an instance of {@link InUseException }
     * 
     */
    public InUseException createInUseException() {
        return new InUseException();
    }

    /**
     * Create an instance of {@link Unsubscribe }
     * 
     */
    public Unsubscribe createUnsubscribe() {
        return new Unsubscribe();
    }

    /**
     * Create an instance of {@link DepleteEPCCache }
     * 
     */
    public DepleteEPCCache createDepleteEPCCache() {
        return new DepleteEPCCache();
    }

    /**
     * Create an instance of {@link UndefineRNGResult }
     * 
     */
    public UndefineRNGResult createUndefineRNGResult() {
        return new UndefineRNGResult();
    }

    /**
     * Create an instance of {@link Poll }
     * 
     */
    public Poll createPoll() {
        return new Poll();
    }

    /**
     * Create an instance of {@link EPCCacheSpecValidationException }
     * 
     */
    public EPCCacheSpecValidationException createEPCCacheSpecValidationException() {
        return new EPCCacheSpecValidationException();
    }

    /**
     * Create an instance of {@link SubscribeResult }
     * 
     */
    public SubscribeResult createSubscribeResult() {
        return new SubscribeResult();
    }

    /**
     * Create an instance of {@link DefineAssocTable }
     * 
     */
    public DefineAssocTable createDefineAssocTable() {
        return new DefineAssocTable();
    }

    /**
     * Create an instance of {@link DefineRNG }
     * 
     */
    public DefineRNG createDefineRNG() {
        return new DefineRNG();
    }

    /**
     * Create an instance of {@link Define }
     * 
     */
    public Define createDefine() {
        return new Define();
    }

    /**
     * Create an instance of {@link RemoveAssocTableEntries }
     * 
     */
    public RemoveAssocTableEntries createRemoveAssocTableEntries() {
        return new RemoveAssocTableEntries();
    }

    /**
     * Create an instance of {@link InvalidURIException }
     * 
     */
    public InvalidURIException createInvalidURIException() {
        return new InvalidURIException();
    }

    /**
     * Create an instance of {@link ReplenishEPCCacheResult }
     * 
     */
    public ReplenishEPCCacheResult createReplenishEPCCacheResult() {
        return new ReplenishEPCCacheResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RNGSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetRNGResult")
    public JAXBElement<RNGSpec> createGetRNGResult(RNGSpec value) {
        return new JAXBElement<RNGSpec>(_GetRNGResult_QNAME, RNGSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEPCCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetEPCCache")
    public JAXBElement<GetEPCCache> createGetEPCCache(GetEPCCache value) {
        return new JAXBElement<GetEPCCache>(_GetEPCCache_QNAME, GetEPCCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CCReports }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "ImmediateResult")
    public JAXBElement<CCReports> createImmediateResult(CCReports value) {
        return new JAXBElement<CCReports>(_ImmediateResult_QNAME, CCReports.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineAssocTable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DefineAssocTable")
    public JAXBElement<DefineAssocTable> createDefineAssocTable(DefineAssocTable value) {
        return new JAXBElement<DefineAssocTable>(_DefineAssocTable_QNAME, DefineAssocTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableValueResult")
    public JAXBElement<String> createGetAssocTableValueResult(String value) {
        return new JAXBElement<String>(_GetAssocTableValueResult_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRNG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetRNG")
    public JAXBElement<GetRNG> createGetRNG(GetRNG value) {
        return new JAXBElement<GetRNG>(_GetRNG_QNAME, GetRNG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CCSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetCCSpecResult")
    public JAXBElement<CCSpec> createGetCCSpecResult(CCSpec value) {
        return new JAXBElement<CCSpec>(_GetCCSpecResult_QNAME, CCSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetSubscribersResult")
    public JAXBElement<ArrayOfString> createGetSubscribersResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetSubscribersResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssocTableValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableValue")
    public JAXBElement<GetAssocTableValue> createGetAssocTableValue(GetAssocTableValue value) {
        return new JAXBElement<GetAssocTableValue>(_GetAssocTableValue_QNAME, GetAssocTableValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssocTableValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "AssocTableValidationException")
    public JAXBElement<AssocTableValidationException> createAssocTableValidationException(AssocTableValidationException value) {
        return new JAXBElement<AssocTableValidationException>(_AssocTableValidationException_QNAME, AssocTableValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReplenishEPCCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "ReplenishEPCCache")
    public JAXBElement<ReplenishEPCCache> createReplenishEPCCache(ReplenishEPCCache value) {
        return new JAXBElement<ReplenishEPCCache>(_ReplenishEPCCache_QNAME, ReplenishEPCCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetVendorVersionResult")
    public JAXBElement<String> createGetVendorVersionResult(String value) {
        return new JAXBElement<String>(_GetVendorVersionResult_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssocTable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTable")
    public JAXBElement<GetAssocTable> createGetAssocTable(GetAssocTable value) {
        return new JAXBElement<GetAssocTable>(_GetAssocTable_QNAME, GetAssocTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EPCPatternList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DepleteEPCCacheResult")
    public JAXBElement<EPCPatternList> createDepleteEPCCacheResult(EPCPatternList value) {
        return new JAXBElement<EPCPatternList>(_DepleteEPCCacheResult_QNAME, EPCPatternList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefineAssocTable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "UndefineAssocTable")
    public JAXBElement<UndefineAssocTable> createUndefineAssocTable(UndefineAssocTable value) {
        return new JAXBElement<UndefineAssocTable>(_UndefineAssocTable_QNAME, UndefineAssocTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetRNGNames")
    public JAXBElement<EmptyParms> createGetRNGNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetRNGNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImplementationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "ImplementationException")
    public JAXBElement<ImplementationException> createImplementationException(ImplementationException value) {
        return new JAXBElement<ImplementationException>(_ImplementationException_QNAME, ImplementationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepleteEPCCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DepleteEPCCache")
    public JAXBElement<DepleteEPCCache> createDepleteEPCCache(DepleteEPCCache value) {
        return new JAXBElement<DepleteEPCCache>(_DepleteEPCCache_QNAME, DepleteEPCCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateNameException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DuplicateNameException")
    public JAXBElement<DuplicateNameException> createDuplicateNameException(DuplicateNameException value) {
        return new JAXBElement<DuplicateNameException>(_DuplicateNameException_QNAME, DuplicateNameException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Unsubscribe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "Unsubscribe")
    public JAXBElement<Unsubscribe> createUnsubscribe(Unsubscribe value) {
        return new JAXBElement<Unsubscribe>(_Unsubscribe_QNAME, Unsubscribe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutAssocTableEntries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "PutAssocTableEntries")
    public JAXBElement<PutAssocTableEntries> createPutAssocTableEntries(PutAssocTableEntries value) {
        return new JAXBElement<PutAssocTableEntries>(_PutAssocTableEntries_QNAME, PutAssocTableEntries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetRNGNamesResult")
    public JAXBElement<ArrayOfString> createGetRNGNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetRNGNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetCCSpecNames")
    public JAXBElement<EmptyParms> createGetCCSpecNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetCCSpecNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchNameException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "NoSuchNameException")
    public JAXBElement<NoSuchNameException> createNoSuchNameException(NoSuchNameException value) {
        return new JAXBElement<NoSuchNameException>(_NoSuchNameException_QNAME, NoSuchNameException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "ParameterException")
    public JAXBElement<ParameterException> createParameterException(ParameterException value) {
        return new JAXBElement<ParameterException>(_ParameterException_QNAME, ParameterException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSubscribers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetSubscribers")
    public JAXBElement<GetSubscribers> createGetSubscribers(GetSubscribers value) {
        return new JAXBElement<GetSubscribers>(_GetSubscribers_QNAME, GetSubscribers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssocTableEntryList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableEntriesResult")
    public JAXBElement<AssocTableEntryList> createGetAssocTableEntriesResult(AssocTableEntryList value) {
        return new JAXBElement<AssocTableEntryList>(_GetAssocTableEntriesResult_QNAME, AssocTableEntryList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidURIException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "InvalidURIException")
    public JAXBElement<InvalidURIException> createInvalidURIException(InvalidURIException value) {
        return new JAXBElement<InvalidURIException>(_InvalidURIException_QNAME, InvalidURIException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchSubscriberException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "NoSuchSubscriberException")
    public JAXBElement<NoSuchSubscriberException> createNoSuchSubscriberException(NoSuchSubscriberException value) {
        return new JAXBElement<NoSuchSubscriberException>(_NoSuchSubscriberException_QNAME, NoSuchSubscriberException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetEPCCacheNames")
    public JAXBElement<EmptyParms> createGetEPCCacheNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetEPCCacheNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidAssocTableEntryException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "InvalidAssocTableEntryException")
    public JAXBElement<InvalidAssocTableEntryException> createInvalidAssocTableEntryException(InvalidAssocTableEntryException value) {
        return new JAXBElement<InvalidAssocTableEntryException>(_InvalidAssocTableEntryException_QNAME, InvalidAssocTableEntryException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAssocTableEntries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "RemoveAssocTableEntries")
    public JAXBElement<RemoveAssocTableEntries> createRemoveAssocTableEntries(RemoveAssocTableEntries value) {
        return new JAXBElement<RemoveAssocTableEntries>(_RemoveAssocTableEntries_QNAME, RemoveAssocTableEntries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableNamesResult")
    public JAXBElement<ArrayOfString> createGetAssocTableNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetAssocTableNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetVendorVersion")
    public JAXBElement<EmptyParms> createGetVendorVersion(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetVendorVersion_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefineEPCCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "UndefineEPCCache")
    public JAXBElement<UndefineEPCCache> createUndefineEPCCache(UndefineEPCCache value) {
        return new JAXBElement<UndefineEPCCache>(_UndefineEPCCache_QNAME, UndefineEPCCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableNames")
    public JAXBElement<EmptyParms> createGetAssocTableNames(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetAssocTableNames_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetEPCCacheNamesResult")
    public JAXBElement<ArrayOfString> createGetEPCCacheNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetEPCCacheNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmptyParms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetStandardVersion")
    public JAXBElement<EmptyParms> createGetStandardVersion(EmptyParms value) {
        return new JAXBElement<EmptyParms>(_GetStandardVersion_QNAME, EmptyParms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Poll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "Poll")
    public JAXBElement<Poll> createPoll(Poll value) {
        return new JAXBElement<Poll>(_Poll_QNAME, Poll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UndefineRNG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "UndefineRNG")
    public JAXBElement<UndefineRNG> createUndefineRNG(UndefineRNG value) {
        return new JAXBElement<UndefineRNG>(_UndefineRNG_QNAME, UndefineRNG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InUseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "InUseException")
    public JAXBElement<InUseException> createInUseException(InUseException value) {
        return new JAXBElement<InUseException>(_InUseException_QNAME, InUseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetStandardVersionResult")
    public JAXBElement<String> createGetStandardVersionResult(String value) {
        return new JAXBElement<String>(_GetStandardVersionResult_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ALEException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "ALEException")
    public JAXBElement<ALEException> createALEException(ALEException value) {
        return new JAXBElement<ALEException>(_ALEException_QNAME, ALEException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineEPCCache }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DefineEPCCache")
    public JAXBElement<DefineEPCCache> createDefineEPCCache(DefineEPCCache value) {
        return new JAXBElement<DefineEPCCache>(_DefineEPCCache_QNAME, DefineEPCCache.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EPCPatternList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "UndefineEPCCacheResult")
    public JAXBElement<EPCPatternList> createUndefineEPCCacheResult(EPCPatternList value) {
        return new JAXBElement<EPCPatternList>(_UndefineEPCCacheResult_QNAME, EPCPatternList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAssocTableEntries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableEntries")
    public JAXBElement<GetAssocTableEntries> createGetAssocTableEntries(GetAssocTableEntries value) {
        return new JAXBElement<GetAssocTableEntries>(_GetAssocTableEntries_QNAME, GetAssocTableEntries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "SecurityException")
    public JAXBElement<SecurityException> createSecurityException(SecurityException value) {
        return new JAXBElement<SecurityException>(_SecurityException_QNAME, SecurityException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Undefine }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "Undefine")
    public JAXBElement<Undefine> createUndefine(Undefine value) {
        return new JAXBElement<Undefine>(_Undefine_QNAME, Undefine.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RNGValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "RNGValidationException")
    public JAXBElement<RNGValidationException> createRNGValidationException(RNGValidationException value) {
        return new JAXBElement<RNGValidationException>(_RNGValidationException_QNAME, RNGValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EPCPatternList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetEPCCacheContentsResult")
    public JAXBElement<EPCPatternList> createGetEPCCacheContentsResult(EPCPatternList value) {
        return new JAXBElement<EPCPatternList>(_GetEPCCacheContentsResult_QNAME, EPCPatternList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidPatternException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "InvalidPatternException")
    public JAXBElement<InvalidPatternException> createInvalidPatternException(InvalidPatternException value) {
        return new JAXBElement<InvalidPatternException>(_InvalidPatternException_QNAME, InvalidPatternException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CCReports }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "PollResult")
    public JAXBElement<CCReports> createPollResult(CCReports value) {
        return new JAXBElement<CCReports>(_PollResult_QNAME, CCReports.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefineRNG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DefineRNG")
    public JAXBElement<DefineRNG> createDefineRNG(DefineRNG value) {
        return new JAXBElement<DefineRNG>(_DefineRNG_QNAME, DefineRNG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAssocTableEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "RemoveAssocTableEntry")
    public JAXBElement<RemoveAssocTableEntry> createRemoveAssocTableEntry(RemoveAssocTableEntry value) {
        return new JAXBElement<RemoveAssocTableEntry>(_RemoveAssocTableEntry_QNAME, RemoveAssocTableEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssocTableSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetAssocTableResult")
    public JAXBElement<AssocTableSpec> createGetAssocTableResult(AssocTableSpec value) {
        return new JAXBElement<AssocTableSpec>(_GetAssocTableResult_QNAME, AssocTableSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCCSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetCCSpec")
    public JAXBElement<GetCCSpec> createGetCCSpec(GetCCSpec value) {
        return new JAXBElement<GetCCSpec>(_GetCCSpec_QNAME, GetCCSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CCSpecValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "CCSpecValidationException")
    public JAXBElement<CCSpecValidationException> createCCSpecValidationException(CCSpecValidationException value) {
        return new JAXBElement<CCSpecValidationException>(_CCSpecValidationException_QNAME, CCSpecValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterForbiddenException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "ParameterForbiddenException")
    public JAXBElement<ParameterForbiddenException> createParameterForbiddenException(ParameterForbiddenException value) {
        return new JAXBElement<ParameterForbiddenException>(_ParameterForbiddenException_QNAME, ParameterForbiddenException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EPCCacheSpec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetEPCCacheResult")
    public JAXBElement<EPCCacheSpec> createGetEPCCacheResult(EPCCacheSpec value) {
        return new JAXBElement<EPCCacheSpec>(_GetEPCCacheResult_QNAME, EPCCacheSpec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEPCCacheContents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetEPCCacheContents")
    public JAXBElement<GetEPCCacheContents> createGetEPCCacheContents(GetEPCCacheContents value) {
        return new JAXBElement<GetEPCCacheContents>(_GetEPCCacheContents_QNAME, GetEPCCacheContents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EPCCacheSpecValidationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "EPCCacheSpecValidationException")
    public JAXBElement<EPCCacheSpecValidationException> createEPCCacheSpecValidationException(EPCCacheSpecValidationException value) {
        return new JAXBElement<EPCCacheSpecValidationException>(_EPCCacheSpecValidationException_QNAME, EPCCacheSpecValidationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Subscribe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "Subscribe")
    public JAXBElement<Subscribe> createSubscribe(Subscribe value) {
        return new JAXBElement<Subscribe>(_Subscribe_QNAME, Subscribe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "GetCCSpecNamesResult")
    public JAXBElement<ArrayOfString> createGetCCSpecNamesResult(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_GetCCSpecNamesResult_QNAME, ArrayOfString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateSubscriptionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "DuplicateSubscriptionException")
    public JAXBElement<DuplicateSubscriptionException> createDuplicateSubscriptionException(DuplicateSubscriptionException value) {
        return new JAXBElement<DuplicateSubscriptionException>(_DuplicateSubscriptionException_QNAME, DuplicateSubscriptionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Immediate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "Immediate")
    public JAXBElement<Immediate> createImmediate(Immediate value) {
        return new JAXBElement<Immediate>(_Immediate_QNAME, Immediate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidEPCException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "InvalidEPCException")
    public JAXBElement<InvalidEPCException> createInvalidEPCException(InvalidEPCException value) {
        return new JAXBElement<InvalidEPCException>(_InvalidEPCException_QNAME, InvalidEPCException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Define }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:epcglobal:alecc:wsdl:1", name = "Define")
    public JAXBElement<Define> createDefine(Define value) {
        return new JAXBElement<Define>(_Define_QNAME, Define.class, null, value);
    }

}
