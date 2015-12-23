#安装docker
#将镜像pull到本地
sudo docker pull b.gcr.io/tensorflow/tensorflow
#如果无法pull，可以翻墙下载镜像，打成tensorflow.tat
sudo docker load < /path/to/tensorflow.tar
#运行docker tensorflow实例
#挂载目录code和mnist都存docker目录下
sudo docker run --rm -it -v /usr/local/workspace/docker/:/docker -w /docker b.gcr.io/tensorflow/tensorflow