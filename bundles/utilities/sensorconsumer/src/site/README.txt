OW2 :: Chameleon :: WireAdmin to EventAdmin Bridge
  
This bundle provides a bridge between WireAdmin and EventAdmin

The bridge consumes sensors data from WireAdmin'wires connected to WireAdmin'producers. Then it wraps the data in an event and send it asynchronnously to an EventAdmin topic.
The WireAdmin'wires between the bridge and the producers are automatically managed by the WireAdminBinder (see src/main/resources/WAB-INF/wireapp.wadl.


WireAdminBinder documentation
* http://svn.apache.org/viewvc/felix/sandbox/donsez/wireadminbinder/src/site/readme.html?view=co
* http://svn.apache.org/viewvc/felix/sandbox/donsez/wireadminbinder/src/site/wireapp.dtd?view=co        
