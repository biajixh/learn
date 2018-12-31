#coding=utf-8
from __future__ import unicode_literals
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
import requests
import urllib2
import re
import csv
def getSongInfoById(id):
    url1='https://music.163.com/song?id='+id
    header1={
    'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
    }
    request1 = urllib2.Request(url1,headers=header1)
    text1 = urllib2.urlopen(request1).read().decode(u'utf-8')
    pa1 = re.compile(u'<script type="application/ld\+json">\n.+?</script>',re.DOTALL)
    s = pa1.findall(text1)
    info = s[0].split('\n')
    info_dic = "{"+info[5]+info[7]+"}"
    return eval(info_dic)  
def getPlayListById(id):
    url='https://music.163.com/playlist?id='+id
    header={
        'User-Agent':"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1"
    }
    request = urllib2.Request(url,headers=header)
    text = urllib2.urlopen(request).read().decode(u'utf-8')
    pa = re.compile(u'<a\shref="/song\?id=\d{1,20}">.+?</a>')
    dataList = pa.findall(text)
    playlist = []
    for data in dataList:
        data = re.sub(u'<a\shref="/song\?id=','',data)
        data = re.sub(u'">.+?</a>','',data)
        songInfo = getSongInfoById(data)
        description = songInfo['description'].split(u'。')
        songInfo['artist'] = re.sub(u'歌手：','',description[0])
        songInfo['album'] = re.sub(u'所属专辑：','',description[1])
        del songInfo['description']
        playlist.append(songInfo)
    return playlist
l_list = getPlayListById(u'110504446')
with open('playlist1.csv','wb') as f:
    w = csv.writer(f)
    fieldnames = l_list[0].keys()
    w.writerow(fieldnames)
    for info in l_list:
        w.writerow(info.values())

