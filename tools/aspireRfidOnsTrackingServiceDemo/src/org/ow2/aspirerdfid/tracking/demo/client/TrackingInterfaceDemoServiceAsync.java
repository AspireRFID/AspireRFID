package org.ow2.aspirerdfid.tracking.demo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * @author Mourtzoukos Konstantinos {email: komo@ait.edu.gr}
 *
 */
public interface TrackingInterfaceDemoServiceAsync {

	void getTagData(String tag, AsyncCallback<List<TagEventSerialObject>> callback);

}
