package testScripts;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerTest implements IRetryAnalyzer{

	int retryAttemptCounter=0;
	int maxRetryCount = 2;
	public boolean retry(ITestResult result) {
		if(!result.isSuccess()) {
			if(retryAttemptCounter < maxRetryCount) {
				retryAttemptCounter++;
				return true;
			}
		}
		return false;
	}

}
