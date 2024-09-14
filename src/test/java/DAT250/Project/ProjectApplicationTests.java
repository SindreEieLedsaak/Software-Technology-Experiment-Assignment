package DAT250.Project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void scenarioTest() throws Exception {
		// 1. Create a new user
		MvcResult user1Result = mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \"user1\", \"email\": \"user1@example.com\"}"))
				.andExpect(status().isOk())
				.andReturn();

		String user1Id = extractIdFromResult(user1Result);

		// 2. List all users (-> shows the newly created user)
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*.username").value("user1"));

		// 3. Create another user
		MvcResult user2Result = mockMvc.perform(post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \"user2\", \"email\": \"user2@example.com\"}"))
				.andExpect(status().isOk())
				.andReturn();

		String user2Id = extractIdFromResult(user2Result);

		// 4. List all users again (-> shows two users)
		mockMvc.perform(get("/api/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2))) // Check the map has 2 entries
				.andExpect(jsonPath("$.*.username", containsInAnyOrder("user1", "user2"))); // Check for both usernames

		// 5. User 1 creates a new poll
		MvcResult pollResult = mockMvc.perform(post("/api/polls")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"question\": \"What is your favorite color?\", \"publishedAt\": \"2024-09-02T10:00:00Z\", \"validUntil\": \"2024-09-09T10:00:00Z\"}"))
				.andExpect(status().isOk())
				.andReturn();

		String pollId = extractIdFromResult(pollResult);

		// 6. List polls (-> shows the new poll)
		mockMvc.perform(get("/api/polls"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1))) // Check that there's one poll (map entry)
				.andExpect(jsonPath("$.*.question", hasItem("What is your favorite color?"))); // Use wildcard to match
																								// "question" field in
																								// the map

		// 7. User 2 votes on the poll
		MvcResult voteResult = mockMvc.perform(post("/api/votes")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"pollId\": \"" + pollId
						+ "\", \"voteOption\": {\"caption\": \"Blue\", \"presentationOrder\": 1}}"))
				.andExpect(status().isOk())
				.andReturn();

		String voteId = extractIdFromResult(voteResult);

		// 8. User 2 changes his vote
		mockMvc.perform(put("/api/votes/" + voteId)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"pollId\": \"" + pollId
						+ "\", \"voteOption\": {\"caption\": \"Green\", \"presentationOrder\": 1}}"))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/votes"))
				.andExpect(status().isOk()) // Check if the status is OK (200)
				.andExpect(jsonPath("$.length()", is(1))) // Check if there is exactly 1 vote
				.andExpect(jsonPath("$.*.voteOption.caption", hasItem("Green"))); // Use wildcard to check the "caption"

		// 10. Delete the one poll
		mockMvc.perform(delete("/api/polls/" + pollId))
				.andExpect(status().isOk());

	}

	private String extractIdFromResult(MvcResult result) throws Exception {
		String content = result.getResponse().getContentAsString();
		// Parsing the key from the JSON object
		return content.substring(2, content.indexOf("\":"));
	}
}
