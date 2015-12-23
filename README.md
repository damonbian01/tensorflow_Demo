#安装docker
sudo apt-get update			--同步源索引
sudo apt-get install -y docker.io
sudo ln -sf /usr/bin/docker.io /usr/local/bin/docker
#将镜像pull到本地
sudo docker pull b.gcr.io/tensorflow/tensorflow
#如果无法pull，可以翻墙下载镜像，打成tensorflow.tat
##download path:http://pan.baidu.com/s/1bnR2kWn
sudo docker load < /path/to/tensorflow.tar
#运行docker tensorflow实例
#挂载目录code和mnist都存docker目录下
sudo docker run --rm -it -v /usr/local/workspace/docker/:/docker -w /docker b.gcr.io/tensorflow/tensorflow
