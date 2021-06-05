# as-svrmap client

On IBM i you have a list of well-known ports. These ports internally are always called by name. (for example as-svrmap or as-signon)
There is a TCP server called "as-svrmap" which can be used to resolve the port number by name. It's similar to the function of a DNS server but only for ports.

This application was created as a little evening project while learing more about the IBM i network protocols and kotlin. This application was tested via the public IBM i "PUB400".

# Usage

1. clone repository
2. gradle run

The method `getServerPort` is called as follows:
`App().getServerPort("pub400.com", "as-svrmap")`

You pass the hostname ("pub400.com" in our example) and the servername ("as-svrmap" in our example) and you'll get the portnumber as integer or a exception is thrown if the server isn't known and no port was found.