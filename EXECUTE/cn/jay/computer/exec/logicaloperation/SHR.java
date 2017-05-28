package cn.jay.computer.exec.logicaloperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.dataregister.CX;

public class SHR extends Execution {
	public SHR(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BaseRegister env = Environment.getDataSegment();

			BIU.getInstruction();

			boolean V = getOperand("D").equals("1");
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			byte[] cl = null;
			if (V) {
				cl = CX.getCL();
			} else {
				cl = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0 };
			}
			byte[] a2 = null;
			if (addr == null) {
				try {
					a2 = RegisterMgr.getDATA(RM, W);
					if (W) {
						LongALU.shr16(a2, cl, RM.equals("000"));
					} else {
						LongALU.shr16(a2, cl, RM.equals("000"));
					}
					RegisterMgr.setDATA(RM, W, a2);
				} catch (Exception e) {
				}
			} else {
				a2 = Memoryer.read(addr, env.getDATA(), W);
				try {
					if (W) {
						LongALU.shr16(a2, cl, false);
					} else {
						LongALU.shr16(a2, cl, false);
					}
				} catch (Exception e) {
				}
				Memoryer.write(addr, env.getDATA(), a2, W);
			}

			break;
		}
		}
	}

}