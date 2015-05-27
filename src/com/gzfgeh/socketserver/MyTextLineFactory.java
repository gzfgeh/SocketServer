package com.gzfgeh.socketserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyTextLineFactory implements ProtocolCodecFactory{

	private MyTextLineDecoder decoder;
	private MyTextLineEncoder encoder;
	private MyTextLineCumulativeDecode cumulativeDecode;
	
	public MyTextLineFactory() {
		decoder = new MyTextLineDecoder();
		encoder = new MyTextLineEncoder();
		cumulativeDecode = new MyTextLineCumulativeDecode();
	}

	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return cumulativeDecode;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}

}
