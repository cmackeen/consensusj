package com.msgilligan.bitcoinj.rpcserver;

import com.msgilligan.bitcoinj.json.pojo.ServerInfo;

/**
 * Standard Bitcoin JSON-RPC service
 */
//@JsonRpcService("BitcoinRPC")
public interface BitcoinJsonRpc {
    public Integer getblockcount();
    public Integer getconnectioncount();
    // TODO: For some reason getinfo doesn't work if we say it retuns ServerInfo
    public Object getinfo();
}
