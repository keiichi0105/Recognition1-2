package recognition1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

   public class Recognition1_lib {
	   VisualRecognition service ;
	   IamOptions iamOptions ;
	   DetectFacesOptions detectFacesOptions = null;
	   
	public Recognition1_lib() {
		service = new VisualRecognition("2018-03-19") ;
		iamOptions = new IamOptions.Builder()
				  .apiKey("3Bro6z9sjlf57yThWHZAMSHQVJUvYfxJPAIJnTz2OajJ")
				  .build();
		service.setIamCredentials(iamOptions);
				
	}

	public DetectedFaces result(String file) {
		// TODO Auto-generated method stub
		try {

			detectFacesOptions = new DetectFacesOptions.Builder()

			  .imagesFile(new File(file))

			  .build();

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		

		DetectedFaces result = service.detectFaces(detectFacesOptions).execute();

		System.out.println(result);
		return result;
	}

	public void Json(DetectedFaces result) {
		// TODO Auto-generated method stub
		//===json

		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = null;

		try {

			node = mapper.readTree(String.valueOf(result));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		int age_min = node.get("images").get(0).get("faces").get(0).get("age").get("min").asInt();

		int age_max = node.get("images").get(0).get("faces").get(0).get("age").get("max").asInt();

		double age_score = node.get("images").get(0).get("faces").get(0).get("age").get("score").doubleValue();

		int gender;

		if(node.get("images").get(0).get("faces").get(0).get("gender").get("gender").toString() == "MALE") {

			gender = 0;

		}else{

			gender = 1;

		}

		double gender_score = node.get("images").get(0).get("faces").get(0).get("gender").get("score").doubleValue();

		 System.out.println("age_min : " + age_min);

		
	}
}
