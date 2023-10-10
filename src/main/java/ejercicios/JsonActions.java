package ejercicios;

import org.json.JSONObject;

public class JsonActions {
    public boolean compareJSONs(JSONObject expectedResult, JSONObject actualResult){
        System.out.println(expectedResult);
        System.out.println("-------------");
        System.out.println(actualResult);
        if(expectedResult.length()!=actualResult.length()){
            return false;
        } else {
            for(String keyE : expectedResult.keySet()){
                if(expectedResult.get(keyE).equals("ignore")){
                    continue;
                } else if(!actualResult.get(keyE).equals(expectedResult.get(keyE))){
                    return false;
                }
            }
        }
        return true;
    }
}
