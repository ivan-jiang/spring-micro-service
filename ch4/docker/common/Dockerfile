FROM centos:centos7
RUN adduser deploy
RUN cd /home/deploy
WORKDIR /home/deploy
RUN yum install -y wget
RUN wget https://mirrors.tuna.tsinghua.edu.cn/apache/hbase/2.2.6/hbase-2.2.6-bin.tar.gz
RUN tar xzvf hbase-2.2.6-bin.tar.gz

