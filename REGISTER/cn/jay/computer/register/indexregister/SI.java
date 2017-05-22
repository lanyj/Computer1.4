package cn.jay.computer.register.indexregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class SI extends BaseRegister {
	public static final SI _SI = new SI();
	
	public static final void setSI(byte[] data) throws CopyArrayException {
		_SI.setDATA(data);
	}
	public static final byte[] getSI() {
		return _SI.getDATA();
	}
}
