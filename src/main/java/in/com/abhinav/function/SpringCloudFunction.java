/**
 * 
 */
package in.com.abhinav.function;

import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import software.amazon.lambda.powertools.parameters.ParamManager;
import software.amazon.lambda.powertools.parameters.SSMProvider;

/**
 * @author abhinav
 *
 */
@Component
public class SpringCloudFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent t) {
		SSMProvider ssmProvider = ParamManager.getSsmProvider();
		Map<String, String> values = ssmProvider.getMultiple("/my/path/prefix");
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);
		response.setBody("Hello, " + t.getBody() + ". " + values.get("test1"));
		return response;
	}

}
