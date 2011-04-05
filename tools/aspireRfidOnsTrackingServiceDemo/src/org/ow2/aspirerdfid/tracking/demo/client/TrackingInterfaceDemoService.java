package org.ow2.aspirerdfid.tracking.demo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("datasource")
public interface TrackingInterfaceDemoService extends RemoteService {

	List<TagEventSerialObject> getTagData(String tag);
}