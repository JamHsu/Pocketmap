import sys
import json
import urllib2
import csv
import argparse
import urlparse

import tabelog_parser
from bs4 import BeautifulSoup
from model import tabelog
from utils import logger

LOG = logger.createLogger("crawler")

def read_data_from_csv(path):
    result_list = list()
    with open(path) as csv_file:
        reader = csv.DictReader(csv_file, delimiter=' ')
        for row in reader:
            result_list.append(row)
    return result_list

def write_to_csv(restaurant_list, file_name):
    with open(file_name, 'w') as csvfile:
        write_header = False
        for restaurant in restaurant_list:
            if write_header is False:
                field_name = vars(restaurant).keys()
                writer = csv.DictWriter(csvfile, fieldnames=field_name)
                writer.writeheader()
                write_header = True
            writer.writerow(vars(restaurant))

def paring_arguments():
    parser = argparse.ArgumentParser()

    parser.add_argument('-s', dest='source_file_path',
                        help='the source csv file path.')

    parser.add_argument('-t', dest='target_file_path',
                        help='the process result file path.')

    return parser.parse_args()

if __name__ == '__main__':
    print '----- Start crawler tabelog data ----'
    try:
        args = paring_arguments()
        csv_source_path = args.source_file_path
        result_csv_path = args.target_file_path
        data_list = read_data_from_csv(csv_source_path)
        restaurant_list = list()
        for data in data_list:
            # TODO: use gevent to improve performance
            restaurant = tabelog_parser.parse_tabelog_to_restaurant(
                                                                data['url'])
            if restaurant:
                restaurant.note = data['note']
                restaurant_list.append(restaurant)


        write_to_csv(restaurant_list, result_csv_path)
        print '----- Finish crawler tabelog data ----'
    except Exception as e:
        LOG.exception('Occur exception, cause by %s', e.message)
        sys.exit(1)

