#coding=utf-8
googol = 10**100
print(googol)
msg = "hello world,from biaji_xuhao."
print(msg[0:28:2])
print(len(msg))
s = msg.split('o')
print(s)
print(s[0]*3+s[1])
s[0] = "d"
print(s)
s.append('bbqskr')
print(s)
s.insert(2,'krei')
print(s)
del s[1]
print(s)
s.remove('bbqskr')
print(s)
temp = 'd' in s
print(temp)
temp = 'bbqskr' in s
print(temp)
print(s.count('d'))
s = " ".join(s)
print(s)
s1 = s.split(" ")
print(s1)
s = sorted(s1)
print(s)
print(s1)
s2 = [1,
    '2',
    [3,'xusho',[3.0,4.0],(222,5.0,'biaji')],
    ]
print(s2)
print(s2[2][3][0])
english = 'monday','tuesday','wednesday'
chinese = '周一','周二','周三'
ls = list(zip(english,chinese))
print(ls)
numbers = range(0,11)
number_thing = (number for number in numbers)
print(type(number_thing))
for number in number_thing:
    print(number)
def print_fuck():
    print('fuck')
print_fuck()
def agree():
    return True
if agree():
    print_fuck()
else:
    print('no this F-word')
def echo(anything):
    return anything+' '+anything
print(echo('11234567'))
def menu(wine, entree, desster):
    return {'wine':wine,'entree':entree,'desster':desster}
print(menu('chardonnary','chicken','cake'))
print(menu(entree='beef',desster='bagel',wine='bordeaux'))
def menu1(wine,entree,desster='pudding'):
    return {'wine':wine,'entree':entree,'desster':desster}
print(menu1('chardonnary','chicken'))
#这里默认值为一个空列表，由于默认值是在定义一个函数是就已经计算好，所以在第二次调用时
#直接取第一次计算好的result列表，输出为['a','b']
def buggy(arg,result=[]):
    result.append(arg)
    print(result)
buggy('a')
buggy('b')
def works(arg):
    result = []
    result.append(arg)
    print(result)
works('a')
works('b')
def nobuggy(arg,result=None):
    if result is None:
        result = []
    result.append(arg)
    print(result)
nobuggy('a')
nobuggy('b')
#====================================================================#
def print_arg(*args):
    print('Posistional argument tuple:',args)
    for arg in args:
        print(arg)
print_arg('a',['a','b'],{'wine':'dd'})
def print_kwargs(**kwargs):
    print('keyword arguments:',kwargs)
print_kwargs(wine='merlot',entree='mutton',dessert='macaroon')
def echo1(anything):
    'echo1 return its input argument'
    return anything
help(echo1)
#==================================================================#
print(echo1.__doc__)
def answer():
    print(24)
def run_something(func):
    func()
run_something(answer)
print(type(run_something))
def add_args(*args):
    num = 0
    for arg in args:
        num += arg
    print(num)
def run_something_with_args(func,*args):
    func(*args)
run_something_with_args(add_args,1,2,3)
def outer(a,b):
    def inner(c,d):
        return c + d
    return inner(a,b)
print(outer(4,7))
#=======闭包和内部函数=======#
#########内部函数##########
def knights(saying):
    def inner(quote):
        print("We are the knight who say: '%s'" % quote)
    return inner(saying)
print(knights('Ni!'))
###########闭包###########
def knight2(saying):
    def inner2():
        return "We are the knight who say: '%s'" % saying
    return inner2
a = knight2('Duck')
b = knight2('Hasenpfeffer')
print(type(a))
print(type(b))
print(a)
print(b)
print(a())
print(b())
#python的lambda 
def edit_story(words,func):
    for word in words:
        print(func(word))
stairs = ['thud','meow','thud','hiss']
def enliven(word):
    return word.capitalize()+'!'
edit_story(stairs,enliven)
edit_story(stairs,lambda word:word.capitalize() + '!')
#############生成器##########
def my_range(first=0,last=10,step=1):
    number = first
    while number < last:
        yield number#返回值用yield
        number += step
ranger = my_range(1,5)
for x in ranger:
    print(x)
#########装饰器#########
def document_it(func):
    def new_function(*args,**kwargs):
        print('Running function:',func.__name__)
        print('Positionam arguments:', args)
        print('Keyword arguments:', kwargs)
        result = func(*args,**kwargs)
        print('Result:',result)
        return result
    return new_function
@document_it#也可以这样赋值
def add_ints(a,b):
    return a + b
print(add_ints(3,5))
cooler_add_ints = document_it(add_ints)#人工对装饰器赋值
cooler_add_ints(3,5)
add_ints(2,6)
class Person():
    def __init__(self,name):
        self.name = name
hunter = Person('Elmer Fudd')
print('The mighty hunter: ', hunter.name)
#===========================================================#
def unicode_test(value):
    import unicodedata
    name = unicodedata.name(value)
    value2 = unicodedata.lookup(name)
    print('value="%s",name="%s",value2="%s"'%(value,name,value2))
unicode_test('A'.decode("utf-8"))
poem = ''' There is a hello world file ^_^
hahahhahahaha'''
fout = open('2','at')
fout.write(poem)
fout.close
fin = open('2','rt')
p = fin.read()
fin.close()
print(p)
