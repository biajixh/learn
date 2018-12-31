#coding=utf-8
def get_description():
    """Retrun random weather,just like the pros"""
    from random import choice
    possibilities = ['rain','snow','sleet','fog','sun','who knows']
    return choice(possibilities)