package com.jam.pocket.parser;

import com.jam.pocket.bean.tabelog.Address;
import com.jam.pocket.bean.tabelog.TabelogRestaurant;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jam on 2016/12/29.
 */
public class TabelogDataParser implements Parser<TabelogRestaurant> {

    @Override
    public List<TabelogRestaurant> parseCsvFile(String filePath) throws IOException {
        Path file = Paths.get(filePath);
        BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8);
        List<CSVRecord> csvRecordList = parseBufferedReaderToBeans(br);
        List<TabelogRestaurant> restaurantList = convertCSVRecordToRestaurant(csvRecordList);
        br.close();
        return restaurantList;
    }

    private List<TabelogRestaurant> convertCSVRecordToRestaurant(List<CSVRecord> recordList) {
        List<TabelogRestaurant> restaurantList = new ArrayList<TabelogRestaurant>();
        for(CSVRecord record : recordList) {
            TabelogRestaurant restaurant = new TabelogRestaurant();
            restaurant.setTabelogId(record.get("tabelog_id"));
            restaurant.setUrl(record.get("url"));
            restaurant.setImgUrl(record.get("img_url"));
            restaurant.setName(record.get("name"));
            restaurant.setNote(record.get("note"));
            restaurant.setLat(Double.parseDouble(record.get("lat")));
            restaurant.setLng(Double.parseDouble(record.get("lng")));
            restaurant.setRating(Double.parseDouble(record.get("rating")));
            Address address = new Address();
            address.setAddress(record.get("streetAddress"));
            address.setRegino(record.get("address_region"));
            address.setLocality(record.get("address_locality"));
            restaurant.setAddress(address);
            restaurantList.add(restaurant);
        }
        return restaurantList;
    }

    private List<CSVRecord> parseBufferedReaderToBeans(final BufferedReader br) throws IOException {
        CSVParser parser = null;
        List<CSVRecord> records = null;
        parser = new CSVParser(br, CSVFormat.DEFAULT.withHeader());
        records = parser.getRecords();
        if(parser!=null)
            parser.close();
        return records;
    }
}
