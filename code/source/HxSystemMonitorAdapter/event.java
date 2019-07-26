package HxSystemMonitorAdapter;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-07-25 11:13:31 MDT
// -----( ON-HOST: 192.168.241.130

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.Cron;
import com.wm.util.event.Event;
import com.wm.app.b2b.server.event.AsyncEventQueue;
import com.wm.app.b2b.server.event.EventManager;
import com.softwareag.util.IDataMap;
import hx.systemMonitorAdapter.SystemMonitorEvent;
// --- <<IS-END-IMPORTS>> ---

public final class event

{
	// ---( internal utility methods )---

	final static event _instance = new event();

	static event _newInstance() { return new event(); }

	static event _cast(Object o) { return (event)o; }

	// ---( server methods )---




	public static final void addQueue (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addQueue)>> ---
		// @sigtype java 3.5
		Event sample = new SystemMonitorEvent();
		EventManager.addEventTypeAlias("Hx System Monitor Event", sample);
		EventManager.addQueue(new AsyncEventQueue(), sample);
		// --- <<IS-END>> ---

                
	}



	public static final void sendEvent (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sendEvent)>> ---
		// @sigtype java 3.5
		IDataMap pipelineMap = new IDataMap(pipeline);
		new SystemMonitorEvent(
				pipelineMap.getAsString("connectionName"),
				pipelineMap.getAsString("connectionType"),
				pipelineMap.getAsString("errorMessage")).sendOut();
		// --- <<IS-END>> ---

                
	}
}

