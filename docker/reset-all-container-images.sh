#!/bin/bash

# やっていること
# - dockerコンテナとイメージの削除
# - dockerコンテナとイメージの作成
# - dockerコンテナの起動
# - elasticsearchのスキーマ生成

BASE_DIR=$(cd $(dirname $0)/../ && pwd)

HOST_NAME=127.0.0.1
if [ $# -eq 1 ]; then
  HOST_NAME=$1
fi

# docker-compose kill
# docker-compose rm
# 
# docker rmi -f mysql/mysql-server:5.6
# docker rmi -f memcached:1.4
# docker rmi -f docker_kinesalite
# docker rmi -f docker_elasticmq
# docker rmi -f docker_s3proxy
# docker rmi -f docker_elasticsearch
# 
# docker-compose build
# docker-compose up -d
# 
# echo 'Initializing elasticsearch ------------------------------------------------------------------'
# while true; do
#     nc $HOST_NAME 9200 < /dev/null
#     if [ $? -eq 0 ]; then
#         break
#     fi
#     echo 'wait until elasticsearch will be listen ...'
#     sleep 1
# done

create_schema_command="curl -XPUT http://${HOST_NAME}:9200/ad_es_v3/ -d @${BASE_DIR}/es/indexer_stanby_crawler_index.json"
echo $create_schema_command
eval $create_schema_command

create_mapping_command="curl -XPUT http://${HOST_NAME}:9200/ad_es_v3/_mapping/job -d @${BASE_DIR}/es/ad_mapping_v3.json"
echo $create_mapping_command
eval $create_mapping_command

exit 0
