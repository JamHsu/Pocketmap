import json
from urlparse import urlparse


class Restaurant(object):
    def __init__(self, data):
        self.url = data.get('@id', '')
        self.tabelog_id = self.parse_id_from_url(self.url)
        self.name = data.get('name', '').encode('utf8')
        self.img_url = data.get('image', '')
        geo = data.get('geo', {})
        self.lat = geo.get('latitude', 0)
        self.lng = geo.get('longitude', 0)
        agg_rating = data.get('aggregateRating', {})
        self.rating = agg_rating.get('ratingValue',0)
        self.note = None

        address = data.get('address')
        self.streetAddress = address.get('streetAddress').encode('utf8')
        self.address_locality = address.get('addressLocality').encode('utf8')
        self.address_region = address.get('addressRegion').encode('utf8')

    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__,
                          sort_keys=True, indent=4)

    def parse_id_from_url(self, url):
        url = url.encode('utf8')
        url_obj = urlparse(url)
        path = url_obj.path
        path_split = path.split('/')
        tabelog_id = ''
        index = -1
        if path_split:
            while tabelog_id is '':
                tabelog_id = path_split[index]
                index -= 1

        return tabelog_id

    @classmethod
    def hook(cls, data):
        return cls(data)