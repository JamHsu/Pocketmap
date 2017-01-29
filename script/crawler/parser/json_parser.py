import json

class JsonParser(object):

    def parse_from_file(self, jsonFilePath, objectHook):
        mappingPathList = []
        jsonList = self.read_json_file(jsonFilePath)
        for jsonData in jsonList:
            jsonStr = json.dumps(jsonData)
            obj = json.loads(jsonStr, object_hook = objectHook)
            mappingPathList.append(obj)
        return mappingPathList

    def read_json_file(self, filePath):
        with open(filePath) as jsonFile:
            json_data = json.load(jsonFile)
        return json_data


