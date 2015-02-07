//----------------------------------------------------------------------------
// Copyright (C) 2011  Ingrid Nunes
// 
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
// 
// To contact the authors:
// http://inf.ufrgs.br/~ingridnunes/bdi4jade/
//
//----------------------------------------------------------------------------

package Test;

import jade.BootProfileImpl;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.PlatformController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;

import mas.blackboard.blackboard;
import mas.globalScheduling.GlobalSchedulingAgent;
import mas.util.ID;


public class AgentStarter {

	private static final Map<String, Agent> agents;

	 private  Logger log;
	
	static {
		agents = new HashMap<String, Agent>();
//		agents.put(HelloWorldAgent.class.getSimpleName(), new HelloWorldAgent());
		 agents.put(ID.Blackboard.LocalName, new blackboard());
		 agents.put(ID.Customer.LocalName, new TestAgent());
		 agents.put(ID.GlobalScheduler.LocalName, new GlobalSchedulingAgent());
		// agents.put(MyAgent.class.getSimpleName(), new MyAgent());
		// agents.put(NestedCapabilitiesAgent.class.getSimpleName(),
		// new NestedCapabilitiesAgent());
		 
	};

	public static void main(String[] args) {
		/*PropertyConfigurator.configure(AgentStarter.class
				.getResource("log4j.properties"));		*/
		new AgentStarter();
		
	}

	private ProfileImpl bootProfile;
	


	private jade.core.Runtime runtime;



	
	public AgentStarter() {
		String className=AgentStarter.class.getName();
		
		log=LogManager.getLogger(this.getClass());
		System.out.println(log);
		log.info(log.isInfoEnabled());
	
		
		
		List<String> params = new ArrayList<String>();
		params.add("-gui");
//		params.add("-detect-main:false");
		
		
		
		log.info("parameters for console :" +params);

		
		this.bootProfile = new BootProfileImpl(params.toArray(new String[0]));
		
		this.runtime = jade.core.Runtime.instance();
		
		PlatformController controller = runtime
				.createMainContainer(bootProfile);		
		
		
		for (String agentName : agents.keySet()) {
			try {
				AgentController ac = ((AgentContainer) controller)
						.acceptNewAgent(agentName, agents.get(agentName));
				ac.start();
				
				log.info(ac);
			} catch (Exception e) {
				log.error(e);
				System.out.println(e);
			}
		}
	}
}