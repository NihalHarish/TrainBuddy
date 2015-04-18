from scrapy.selector import Selector
from scrapy.http import HtmlResponse
import urllib2
import re
import requests
#requests lib required only for pnr part
'''
READ_ME
pls install requests library and run file as it is
'''


class train_scrape:
	def __init__(self,num):
		self.train_num=num
		#request for the url
		req = urllib2.Request('http://runningstatus.in/status/'+num+'-today')

		#open and read the page/fetch html contents
		res = urllib2.urlopen(req)
		the_page = res.read()

		#create selector object for scraping
		response=Selector(text=the_page)
		
		#store selector object
		self.response=response

		
	def get_status(self):
		#gives info on whether the train is 'running',''Not Started' or 'Arrived'	
		l=self.response.xpath('//div[@class="runningstatus-widget-content"]/p/font/text()').extract()
		status=l[0]
		delay=" ".join(l[1].split()[-2:])
		return status+delay
	
	def get_lastseen(self):
		#Gives the name and time of the last station it has left if status is 'running'
		n=self.response.xpath('//div[@class="runningstatus-widget-content"]/p/b/text()').extract()		
		return n

	def print_path(self):
		#Prints the train path
		for row in self.response.xpath('//tbody/tr'):
			col=row.xpath('.//td/text()').extract()
			print(col[0])

	def get_path_as_a_list(self):
		l=[]
		for row in self.response.xpath('//tbody/tr'):
			col=row.xpath('.//td/text()').extract()
			l.append(re.search(r'(.*)[(]',col[0]).group(1))
		return l

	def get_info(self):
		#returns a list with the station name,arrival,departure of all stations along it's path
		l=[]
		for row in self.response.xpath('//tbody/tr'):
			col=row.xpath('.//td/text()').extract()
			d=dict()
			d['station']=(re.search(r'(.*)[(]',col[0]).group(1))
			d['arrival']=col[2]
			d['departure']=col[3]
			l.append(d)
		return l
	




#PNR class
#requests library is required to use this class
 
class pnr_scrape:
	def __init__(self,num):
		self.pnr=num
		#request for the url
		#req = urllib2.Request('http://ipnrstatus.in/pnr-result.php','lccp_pnrno1='+num)

		#open and read the page/fetch html contents
		res=requests.post('http://www.getpnrstatus.co.in?pnrno=4519282568')
		the_page=res.text		
		
		#print(the_page)
		#create selector object for scraping
		response=Selector(text=the_page)
		
		#store selector object
		self.response=response
		#extract the required info
		p=self.response.xpath('//td/text()').extract()
		self.info=p
		
	
	def get_starting_point(self):
		return(re.search(r'\][ ](.*)',self.info[5]).group(1))
	
	def get_destination_point(self):
		return(re.search(r'\][ ](.*)',self.info[6]).group(1)).strip()

	def get_train_number(self):
		return self.info[1].strip()

	



pnr=pnr_scrape('4519282568')#this one is a valid pnr number
print(pnr.get_train_number())#prints train number
print(pnr.get_starting_point())#starting stn
print(pnr.get_destination_point())#destination stn
trn=train_scrape(pnr.get_train_number())
print(trn.get_status())#train status
print(trn.get_lastseen())#last seen stn only if the train is currently running
print(trn.get_path_as_a_list())#stn path
print(trn.get_info())#other info abt the train














