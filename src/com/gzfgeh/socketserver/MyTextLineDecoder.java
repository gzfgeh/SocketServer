package com.gzfgeh.socketserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyTextLineDecoder implements ProtocolDecoder {

	public void decode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput out)
			throws Exception {
		int startPosition = ioBuffer.position();
		while (ioBuffer.hasRemaining()){
			byte b = ioBuffer.get();
			if (b == '\n'){
				int currentPosition = ioBuffer.position();
				int limit = ioBuffer.limit();
				ioBuffer.position(startPosition);
				ioBuffer.limit(currentPosition);
				IoBuffer buffer = ioBuffer.slice();
				byte[] dest = new byte[buffer.limit()];
				buffer.get(dest);
				String str = new String(dest);
				out.write(str);
				ioBuffer.position(currentPosition);
				ioBuffer.limit(limit);
			}
		}
	}

	public void dispose(IoSession session) throws Exception {
		
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		
	}

}
