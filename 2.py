#coding=utf-8
from __future__ import unicode_literals
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
from threading import Timer
from wxpy import *
import time
bot = Bot()
def send_news():
    try:
        tm = time.strftime("%H:%M",time.localtime())
        print tm
        if(tm == "20:14" or tm == "12:13"):
            print "send_seccuss"
            my_friend = bot.friends().search(u'清晨的落叶')[0]
            my_friend.send(u"记得吃药哦")
        t = Timer(60,send_news)
        t.start()
    except:
        print(u"失败")
if __name__ == "__main__":
    send_news()