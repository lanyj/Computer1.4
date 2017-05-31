package cn.jay.computer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cn.jay.computer.clk.CLK;
import cn.jay.computer.port.PortMgr;
import cn.jay.computer.tcp.Client;
import cn.jay.modelprovider.ModelMgr;

public class ComputerEnder {
	public static final void end() {
		Set<Entry<Integer, CLK>> clk = ModelMgr.CLK_POOL.entrySet();
		for (Iterator<Entry<Integer, CLK>> itr = clk.iterator(); itr.hasNext();) {
			itr.next().getValue().close();
		}
		
		PortMgr.PORT_SERVER.close();
		
		Set<Entry<String, Client>> mods = ModelMgr.IO_MODELS.entrySet();
		for (Iterator<Entry<String, Client>> mod = mods.iterator(); mod.hasNext();) {
			mod.next().getValue().close();
		}
	}
}