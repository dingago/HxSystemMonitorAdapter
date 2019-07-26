package HxSystemMonitorAdapter;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-07-22 15:42:07 MDT
// -----( ON-HOST: 192.168.241.166

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import hx.systemMonitorAdapter.SystemMonitorAdapter;
// --- <<IS-END-IMPORTS>> ---

public final class adapter

{
	// ---( internal utility methods )---

	final static adapter _instance = new adapter();

	static adapter _newInstance() { return new adapter(); }

	static adapter _cast(Object o) { return (adapter)o; }

	// ---( server methods )---




	public static final void registerAdapter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(registerAdapter)>> ---
		// @sigtype java 3.5
		IData input = IDataFactory.create();
		IDataCursor inputCursor = input.getCursor();
		IDataUtil.put(inputCursor, "adapter",
				SystemMonitorAdapter.getInstance());
		inputCursor.destroy();
		try {
			Service.doInvoke("wm.art.adapter.deployment",
					"registerAdapterType", input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void unregisterAdapter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unregisterAdapter)>> ---
		// @sigtype java 3.5
		IData input = IDataFactory.create();
		IDataCursor inputCursor = input.getCursor();
		IDataUtil.put(inputCursor, "adapter",
				SystemMonitorAdapter.getInstance());
		inputCursor.destroy();
		try {
			Service.doInvoke("wm.art.adapter.deployment",
					"unregisterAdapterType", input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		// --- <<IS-END>> ---

                
	}
}

