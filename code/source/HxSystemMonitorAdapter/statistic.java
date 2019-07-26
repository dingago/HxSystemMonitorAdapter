package HxSystemMonitorAdapter;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-07-25 17:15:08 MDT
// -----( ON-HOST: 192.168.241.130

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
// --- <<IS-END-IMPORTS>> ---

public final class statistic

{
	// ---( internal utility methods )---

	final static statistic _instance = new statistic();

	static statistic _newInstance() { return new statistic(); }

	static statistic _cast(Object o) { return (statistic)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @sigtype java 3.5
		connectionTypeData.clear();
		connectionData.clear();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getConnection (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getConnection)>> ---
		// @sigtype java 3.5
		// [i] field:0:required connectionName
		// [o] field:0:required connectionType
		// [o] field:0:required online
		// [o] field:0:required timestamp
		// [o] field:0:required errorMessage
		IDataMap pipelineMap = new IDataMap(pipeline);
		String connectionName = pipelineMap.getAsString("connectionName");
		
		String connectionType = null;
		String online = null;
		String timestamp = null;
		String errorMessage = null;
		if (connectionData.containsKey(connectionName)){
			StatisticData data = connectionData.get(connectionName);
			String[] instanceTypes = connectionTypeData.keySet().toArray(new String[] {});
			for (int i = 0; i < instanceTypes.length; i++) {
				if (connectionTypeData.get(instanceTypes[i]).contains(data)){
					connectionType = instanceTypes[i];
					break;
				}
			}
			online = String.valueOf(data.online);
			synchronized(format){
				timestamp = format.format(data.timestamp);
			}
			errorMessage = data.errorMessage;
		}
		
		pipelineMap.put("connectionType", connectionType);
		pipelineMap.put("online", online);
		pipelineMap.put("timestamp", timestamp);
		pipelineMap.put("errorMessage", errorMessage);
		// --- <<IS-END>> ---

                
	}



	public static final void getConnectionCount (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getConnectionCount)>> ---
		// @sigtype java 3.5
		// [i] field:0:required connectionType
		// [o] field:0:required totalCount
		// [o] field:0:required onlineCount
		IDataMap pipelineMap = new IDataMap(pipeline);
		String connectionType = pipelineMap.getAsString("connectionType");
		
		int totalCount = 0;
		int onlineCount = 0;
		if (connectionTypeData.containsKey(connectionType)){
			Hashtable<String, StatisticData> instances = connectionTypeData.get(connectionType);
			String[] keys = instances.keySet().toArray(new String[] {});
			for (int i = 0; i < keys.length; i++) {
				StatisticData data = instances.get(keys[i]);
				totalCount ++;
				if (data.online){
					onlineCount ++;
				}
			}
		}
		
		pipelineMap.put("totalCount", String.valueOf(totalCount));
		pipelineMap.put("onlineCount", String.valueOf(onlineCount));
		// --- <<IS-END>> ---

                
	}



	public static final void getConnections (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getConnections)>> ---
		// @sigtype java 3.5
		// [i] field:0:required connectionType
		// [o] record:1:required connections
		// [o] - field:0:required connectionName
		// [o] - field:0:required online
		IDataMap pipelineMap = new IDataMap(pipeline);
		String connectionType = pipelineMap.getAsString("connectionType");
		
		List<IData> connectionsData = new ArrayList<IData>();
		if (connectionTypeData.containsKey(connectionType)){
			Hashtable<String, StatisticData> instances = connectionTypeData.get(connectionType);
			String[] keys = instances.keySet().toArray(new String[] {});
			for (int i = 0; i < keys.length; i++) {
				StatisticData data = instances.get(keys[i]);
				IData instanceIData = IDataFactory.create();
				IDataCursor instanceIDataCursor = instanceIData.getCursor();
				IDataUtil.put(instanceIDataCursor, "connectionName", data.connectionName);
				IDataUtil.put(instanceIDataCursor, "online", String.valueOf(data.online));
				instanceIDataCursor.destroy();
				connectionsData.add(instanceIData);
			}
		}
		
		pipelineMap.put("connections", connectionsData.toArray(new IData[connectionsData.size()]) );
		// --- <<IS-END>> ---

                
	}



	public static final void upsert (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(upsert)>> ---
		// @sigtype java 3.5
		IDataMap pipelineMap = new IDataMap(pipeline);
		String connectionType = pipelineMap.getAsString("connectionType");
		String connectionName = pipelineMap.getAsString("connectionName");
		String online = pipelineMap.getAsString("online");
		String errorMessage = pipelineMap.getAsString("errorMessage");
		
		StatisticData data = new StatisticData(connectionType, connectionName, Boolean.valueOf(online), errorMessage);
		if (!connectionTypeData.containsKey(connectionType)){
			connectionTypeData.put(connectionType, new Hashtable<String, StatisticData>());
		}
		connectionTypeData.get(connectionType).put(connectionName, data);
		connectionData.put(connectionName, data);
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static Hashtable<String, Hashtable<String,StatisticData>> connectionTypeData = new Hashtable<String, Hashtable<String, StatisticData>>();
	private static Hashtable<String, StatisticData> connectionData = new Hashtable<String, StatisticData>();
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private static class StatisticData{
		private String connectionType;
		private String connectionName;
		private boolean online;
		private String errorMessage;
		private Date timestamp;
		
		public StatisticData(String connectionType, String connectionName, boolean online, String errorMessage){
			this.connectionType = connectionType;
			this.connectionName = connectionName;
			this.online = online;
			this.timestamp = new Date();
			this.errorMessage = errorMessage;
		}
	}
	// --- <<IS-END-SHARED>> ---
}

