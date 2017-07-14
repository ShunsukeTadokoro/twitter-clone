FROM java:8

RUN  mkdir -p /opt/s3proxy/data
RUN  cd /opt/s3proxy/ && wget https://github.com/andrewgaul/s3proxy/releases/download/s3proxy-1.4.0/s3proxy

RUN  chmod a+x /opt/s3proxy/s3proxy
ADD ./s3proxy.conf /opt/s3proxy/s3proxy.conf

EXPOSE 9325

WORKDIR /opt/s3proxy
ENTRYPOINT ["/usr/bin/java", "-jar", "s3proxy", "--properties", "/opt/s3proxy/s3proxy.conf"]
