import urllib2
import urlparse
import json

from bs4 import BeautifulSoup

from utils import logger
from model import tabelog

LOG = logger.createLogger("tabelog_parser")

def parse_tabelog_to_restaurant(url):
    try:
        request = urllib2.Request(url)
        response = urllib2.urlopen(request)
        html = response.read()

        soup = BeautifulSoup(html, "html.parser")
        ldjson_data = soup.find('script', type='application/ld+json').text
        data = json.loads(ldjson_data)
        restaurant = tabelog.Restaurant(data)

        location = find_restaurant_lat_lng(soup)
        restaurant.lat = location['lat']
        restaurant.lng = location['lng']
        print 'Parse tabelog url success, url: %s' % url
        return restaurant
    except Exception as e:
        print 'Parse tabelog url failed, url: %s' % url
        LOG.exception('Parse tabelog url failed, url: %s, reason: %s', url,
                      e.message)
        return None

def find_restaurant_lat_lng(soup):
    geo_div = soup.find('tr', {'class': 'address'})
    geo_div = geo_div.find('div', {'class': 'rst-map'})
    geo_url_data = geo_div.find('img').attrs['data-original']
    url_obj = urlparse.urlparse(geo_url_data)
    location_str = dict(urlparse.parse_qsl(url_obj.query))['center']
    location_str.split(',')
    return {
        'lat': location_str.split(',')[0],
        'lng': location_str.split(',')[1]
    }