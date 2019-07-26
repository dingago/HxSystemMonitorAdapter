package HxSystemMonitorAdapter;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-07-25 17:20:53 MDT
// -----( ON-HOST: 192.168.241.130

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void retrieveNestedErrorMessage (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(retrieveNestedErrorMessage)>> ---
		// @sigtype java 3.5
		// [i] field:0:required errorMessage
		// [o] field:0:required nestedErrorMessage
		IDataMap pipelineMap = new IDataMap(pipeline);
		String errorMessage = pipelineMap.getAsString("errorMessage");
		pipelineMap.put("nestedErrorMessage", errorMessage.substring(errorMessage.indexOf("\n") + 1));
		// --- <<IS-END>> ---

                
	}
}

