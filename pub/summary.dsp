<HTML>
<HEAD>
	<TITLE>Hx System Monitor</TITLE>
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
	%invoke HxSystemMonitorAdapter.ui:getRefreshInterval%
	<meta http-equiv="refresh" content="%value refreshInterval%">
	%endinvoke%
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<script src="/WmRoot/webMethods.js.txt"></script>
	<LINK REL="stylesheet" TYPE="text/css" HREF="/WmRoot/webMethods.css">
</HEAD>
<BODY>
<TABLE width="100%">

%ifvar action%
%switch action%
%case 'ping'%
		%invoke HxSystemMonitorAdapter.ui:ping%
			%ifvar errorMessage%
				<TR><TD class="message" colspan="2">
				%value errorMessage encode(none)%
				</TD></TR>
			%else%
				<TR><TD class="message" colspan="2">The system is online</TD></TR>
			%endif%
        %endinvoke%
%endswitch%
%endif%
<TR>
	<TD class="menusection-Settings" colspan="2">
	Hx System Monitor &gt;
	Summary </TD>
</TR>
%invoke HxSystemMonitorAdapter.ui:getSummary%
%loop summaryReport%
<TR>
	<TD class="heading" colspan="2">%value connectionType%</TD>
</TR>
<TR>
	<TD class="evenrowdata" width="10%">
		<H1%ifvar hasError equals('true')% style="color:#F00"%endif%>%value onlineCount%/%value totalCount%</H1>
	</TD>
	<TD class="evenrowdata" width="90%">
	%invoke HxSystemMonitorAdapter.ui:getStatusByType%
	<TABLE width="100%">
		%loop statusReport%
		<TR>
			<TD class="oddrowdata-l" width="70%"><A href="/WmART/ConnNodeDetails.dsp?readOnly=true&connectionAlias=%value connectionName%&adapterTypeName=HxSystemMonitorAdapter&searchConnectionName=">%value connectionName%</A></TD>
			<TD class="oddrowdata" width="15%"><IMG border=0 align="bottom" alt="status" src="/WmRoot/icons/%ifvar online equals('true')%green-ball.gif%else%red-ball.gif%endif%"/></TD>
			<TD class="oddrowdata" width="15%"><A class="submodal" href="summary.dsp?connectionName=%value -urlencode connectionName%&action=ping"><IMG border=0 align="bottom" alt="ping" src="/WmRoot/icons/checkdot.gif"/></A></TD>
		</TR>
		%endloop%
	</TABLE>
	%endinvoke%
	</TD>
</TR>
%endloop%
%endinvoke%
</TABLE>
</BODY>
</HTML>
