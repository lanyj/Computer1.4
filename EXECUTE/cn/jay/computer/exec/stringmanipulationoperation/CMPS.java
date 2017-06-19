package cn.jay.computer.exec.stringmanipulationoperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.indexregister.SI;
import cn.jay.computer.register.segmentregister.ES;

public class CMPS extends Execution {
	public CMPS(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BaseRegister env = Environment.getDataSegment();
			boolean W = getOperand("W").equals("1");

			byte[] src = Memoryer.read(SI.getSI(), env.getDATA(), W);
			byte[] des = Memoryer.read(DI.getDI(), ES.getES(), W);
			LongALU.sub(src, des);

			boolean df = FLAGS.getFLAGS(FLAGS.DF);
			if (df) {
				if (W) {
					SI._SI.dec();
					DI._DI.dec();
				}
				SI._SI.dec();
				DI._DI.dec();
			} else {
				if (W) {
					SI._SI.inc();
					DI._DI.inc();
				}
				SI._SI.inc();
				DI._DI.inc();
			}

			break;
		}
		}
	}

}
