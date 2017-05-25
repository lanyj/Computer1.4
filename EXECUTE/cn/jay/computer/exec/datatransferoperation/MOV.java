package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.utilexception.CopyArrayException;

public class MOV extends Execution {

	/*
	 * D - D W - W MOD - MM REG - RRR RM - rrr
	 * 
	 * 
	 * MOV reg/mem to/from reg 100010dw modregr/m [addr] 
	 * MOV reg/mem to segreg 10001110 modsegr/m (seg = segreg) 
	 * MOV immed to reg/mem 1100011w mod000r/m [addr] data 
	 * MOV immed to reg 1011wreg data 
	 * MOV direct mem to/from acc 101000dw addr
	 */
	public MOV(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BIU.getInstruction();

			boolean D = getOperand("D").equals("1");
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			if (D) {
				try {
					RegisterMgr.setDATA(REG, W, Memoryer.read(addr, Environment.getDataSegment().getDATA(), W));
				} catch (Exception e) {
				}
			} else {
				try {
					Memoryer.write(addr, Environment.getDataSegment().getDATA(), RegisterMgr.getDATA(REG, W), W);
				} catch (Exception e) {
				}
			}

			break;
		}
		case 1:{
			BIU.getInstruction();
			
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			byte[] value = null;
			byte[] low = BIU.getInstruction();
			if(W) {
				byte[] high = BIU.getInstruction();
				value = arrayConcat(low, high);
			} else {
				value = arrayConcat(low, new byte[8]);
			}
			if(addr == null) {
				try {
					RegisterMgr.setDATA(REG, W, value);
				} catch (Exception e) {
				}
			} else {
				Memoryer.write(addr, Environment.getDataSegment().getDATA(), value, W);
			}
			break;
		}
		case 2:{
			boolean W = getOperand("W").equals("1");
			String REG = getOperand("REG");
			
			byte[] value = null;
			byte[] low = BIU.getInstruction();
			if(W) {
				byte[] high = BIU.getInstruction();
				value = arrayConcat(low, high);
			} else {
				value = arrayConcat(low, new byte[8]);
			}
			
			try {
				RegisterMgr.setDATA(REG, W, value);
			} catch (Exception e) {
			}
			break;
		}
		case 3:{
			BIU.getInstruction();
			
			boolean W = getOperand("W").equals("1");
			boolean D = getOperand("D").equals("1");
			
			byte[] addr = null;
			byte[] low = BIU.getInstruction();
			if(W) {
				byte[] high = BIU.getInstruction();
				addr = arrayConcat(low, high);
			} else {
				addr = arrayConcat(low, new byte[8]);
			}
			
			if(D) {
				try {
					AX.setAX(Memoryer.read(addr, Environment.getDataSegment().getDATA(), W));
				} catch (CopyArrayException e) {
				}
			}
			else {
				Memoryer.write(addr, Environment.getDataSegment().getDATA(), AX.getAX(), W);
			}
			break;
		}
		case 4:
			BIU.getInstruction();
			boolean D = getOperand("D").equals("1");
			String MOD = getOperand("MOD");
			String SEG = getOperand("SEG");
			String RM = getOperand("R/M");
			
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			if (D) {
				try {
					RegisterMgr.setDATA(SEG, true, Memoryer.read(addr, Environment.getDataSegment().getDATA(), true));
				} catch (Exception e) {
				}
			} else {
				try {
					Memoryer.write(addr, Environment.getDataSegment().getDATA(), RegisterMgr.getDATA(SEG, true), true);
				} catch (Exception e) {
				}
			}

			break;
		}
	}

}