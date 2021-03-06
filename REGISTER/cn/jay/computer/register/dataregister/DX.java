package cn.jay.computer.register.dataregister;

import cn.jay.computer.arrayutils.CopyArrayException;
import cn.jay.computer.register.baseregister.BaseRegister;

public class DX extends BaseRegister {
	public final static DX _DX = new DX();
	public static final void setDX(byte[] data) throws CopyArrayException {
		
//		System.out.println("Set DX = " + Arrays.toString(data));
		
		_DX.setDATA(data);
	}
	public static final byte[] getDX() {
		return _DX.getDATA();
	}
	public static final void setDH(byte[] data) throws CopyArrayException {
		_DX.setHIGH(data);
	}
	public static final byte[] getDH() {
		return _DX.getHIGH();
	}
	public static final void setDL(byte[] data) throws CopyArrayException {
		_DX.setLOW(data);
	}
	public static final byte[] getDL() {
		return _DX.getLOW();
	}
}
