package com.gzfgeh.socketserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	public static final int PORT = 8989;
	
	public static void main(String[] args) {
		try {
			NioSocketAcceptor acceptor = new NioSocketAcceptor();
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			chain.addLast("mina_chain", new ProtocolCodecFilter(new MyTextLineFactory()));
			acceptor.setHandler(new MinaHandler());
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);
			acceptor.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
