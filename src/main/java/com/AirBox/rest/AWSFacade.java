package com.AirBox.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.RestoreObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;


public class AWSFacade {
	public String addS3BucketObjects( File fileobject,String filetoupload){
		String response="fail";
	
		AWSCredentials myCredentials = new BasicAWSCredentials(
				MyConfig.getMyAccessId(), MyConfig.getMySecretId());
		AmazonS3 s3 = new AmazonS3Client(myCredentials);        
		Region usWest1 = Region.getRegion(Regions.US_WEST_1);
		s3.setRegion(usWest1);
		String bucketName = MyConfig.getMyBucketName();

		System.out.println("===========================================");
		System.out.println("      Getting Started with Amazon S3        ");
		System.out.println("===========================================\n");

		try {
			System.out.println("Uploading a new object to S3...");
			System.out.println("Name of the uploading object is: "+filetoupload);
			s3.putObject(new PutObjectRequest(bucketName, filetoupload, fileobject));
			System.out.println("File uploaded on S3 - location: "+bucketName+" -> "+filetoupload);
			response="success";

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return response;
	}

	
	public String downloadAllS3BucketObjects(String localStoragePath){
		String response="fail";
		AWSCredentials myCredentials = new BasicAWSCredentials(
				MyConfig.getMyAccessId(), MyConfig.getMySecretId());
		AmazonS3 s3 = new AmazonS3Client(myCredentials);        
		Region usWest1 = Region.getRegion(Regions.US_WEST_1);
		s3.setRegion(usWest1);
		String bucketName = MyConfig.getMyBucketName();
		System.out.println("\nDownloading all objects...");

		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon S3");
		System.out.println("===========================================\n");
		GetObjectRequest objRequest=null;
		try{
			ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
			.withBucketName(bucketName));
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				System.out.println(objectSummary.getKey() + " file of size " + objectSummary.getSize() + " downloaded.");
				
				objRequest=new GetObjectRequest(bucketName, objectSummary.getKey());
				File file=new File(localStoragePath+objectSummary.getKey());
				s3.getObject(objRequest,file);
				s3.getObject(objRequest);
				System.out.println("Object "+ objectSummary.getKey() +" downloaded to "+file.getPath());
				System.out.println("-----------------------------------------------------------------");

			}
			response="success";

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return response;
	}
	
	
	public String downloadS3BucketObject(String localStoragePath, String key){
		String response="fail";
		AWSCredentials myCredentials = new BasicAWSCredentials(
				MyConfig.getMyAccessId(), MyConfig.getMySecretId());
		AmazonS3 s3 = new AmazonS3Client(myCredentials);        
		Region usWest1 = Region.getRegion(Regions.US_WEST_1);
		s3.setRegion(usWest1);
		String bucketName = MyConfig.getMyBucketName();
		System.out.println("Downloading object...");

		System.out.println("===========================================");
		System.out.println("  Getting Started with Amazon S3 Download   ");
		System.out.println("===========================================");
		GetObjectRequest objRequest=null;
		try{
				objRequest=new GetObjectRequest(bucketName, key);
				File file=new File(localStoragePath+key);
				s3.getObject(objRequest,file);
				s3.getObject(objRequest);
				System.out.println("Object "+ key +" downloaded to "+file.getPath());
				System.out.println("-----------------------------------------------------------------");

			
			response="success";

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return response;
	}
	
		public String addFolderS3BucketObjects(File directory,String key){
		String response="fail";
		AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest1 = Region.getRegion(Regions.US_WEST_1);
		s3.setRegion(usWest1);
		String bucketName = MyConfig.getMyBucketName();

		System.out.println("===========================================");
		System.out.println("Getting Started with Amazon S3");
		System.out.println("===========================================\n");
		
		try {
			TransferManager tm=new TransferManager(s3);
			tm.uploadDirectory(bucketName, key, directory, true);
			response="success";
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return response;
	}
		
		
		
		public String deleteS3BucketObjects(String key){
			AWSCredentials myCredentials = new BasicAWSCredentials(
					MyConfig.getMyAccessId(), MyConfig.getMySecretId());
			AmazonS3 s3 = new AmazonS3Client(myCredentials); 
			Region usWest1 = Region.getRegion(Regions.US_WEST_1);
			s3.setRegion(usWest1);
			String bucketName = MyConfig.getMyBucketName();
			String response="fail";
			System.out.println("===========================================");
			System.out.println("Getting Started with Amazon S3");
			System.out.println("===========================================\n");
			
			try {
				s3.deleteObject(new DeleteObjectRequest(bucketName, key));
				response="success";
			} catch (AmazonServiceException ase) {
				System.out.println("Caught an AmazonServiceException, which means your request made it "
						+ "to Amazon S3, but was rejected with an error response for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace)
			{
				System.out.println("Caught an AmazonClientException, which means the client encountered "
						+ "a serious internal problem while trying to communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}
			return response;
		}
	
		
		public String restoreS3BucketObjects(String key,int expirationInDays){
			String response="fail";
			AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_1);
			s3.setRegion(usWest2);
			String bucketName = MyConfig.getMyBucketName();

			System.out.println("===========================================");
			System.out.println("Getting Started with Amazon S3");
			System.out.println("===========================================\n");
			
			try {
				RestoreObjectRequest request = new RestoreObjectRequest(bucketName, key, 2);
				s3.restoreObject(request);
				
				response="success";
			} catch (AmazonServiceException ase) {
				System.out.println("Caught an AmazonServiceException, which means your request made it "
						+ "to Amazon S3, but was rejected with an error response for some reason.");
				System.out.println("Error Message:    " + ase.getMessage());
				System.out.println("HTTP Status Code: " + ase.getStatusCode());
				System.out.println("AWS Error Code:   " + ase.getErrorCode());
				System.out.println("Error Type:       " + ase.getErrorType());
				System.out.println("Request ID:       " + ase.getRequestId());
			} catch (AmazonClientException ace) {
				System.out.println("Caught an AmazonClientException, which means the client encountered "
						+ "a serious internal problem while trying to communicate with S3, "
						+ "such as not being able to access the network.");
				System.out.println("Error Message: " + ace.getMessage());
			}
			return response;
		}
		
		
		 private static void displayTextInputStream(InputStream input) throws IOException {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		        while (true) {
		            String line = reader.readLine();
		            if (line == null) break;

		            System.out.println("    " + line);
		        }
		        System.out.println();
		    }


}