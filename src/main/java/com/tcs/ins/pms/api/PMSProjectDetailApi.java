package com.tcs.ins.pms.api;

import static com.tcs.ins.support.Constant.REQUEST_PARAM_MAPPING_PROJECT;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_PAGE_NUMBER;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_PAGE_SIZE;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_SORT_BY;
import static com.tcs.ins.support.Constant.REQUEST_PARAM_SORT_DIRECTION;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.ins.pms.service.AsynchronousService;
import com.tcs.ins.pms.service.PMSService;
import com.tcs.ins.pms.service.model.ProjectDetailModel;
import com.tcs.ins.pms.service.model.ProjectDetailSearchRequest;

@RestController
@RequestMapping(REQUEST_PARAM_MAPPING_PROJECT)
class PMSProjectDetailApi {


	private static final Logger logger = LoggerFactory.getLogger(PMSProjectDetailApi.class);

	private static final String SORT_DIRECTION_ASC = "asc";
	private final PMSService pMSService;

	public PMSProjectDetailApi(PMSService pMSService) {
		this.pMSService = pMSService;
	}

	@Autowired
	private AsynchronousService asynchronousService;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProjectDetailModel> getProject(@PathVariable Long id) {
		return ResponseEntity.ok(pMSService.getProject(id));
	}

	@GetMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Page<ProjectDetailModel>> searchProject(
			@RequestParam(name = REQUEST_PARAM_PAGE_NUMBER, required = true) Integer pageNumber,
			@RequestParam(name = REQUEST_PARAM_PAGE_SIZE, required = true) Integer pageSeize,
			@RequestParam(name = REQUEST_PARAM_SORT_BY, required = false) String sortBy,
			@RequestParam(name = REQUEST_PARAM_SORT_DIRECTION, required = false) String sortDirection,
			@RequestParam Map<String, String> requestParam) {
		ProjectDetailSearchRequest searchRequest = new ProjectDetailSearchRequest(requestParam);
		PageRequest pageRequest = pageRequest(pageNumber, pageSeize, sortBy, sortDirection);
		Page<ProjectDetailModel> page = pMSService.search(pageRequest, searchRequest);
		return ResponseEntity.ok(page);
	}

	private PageRequest pageRequest(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		if (StringUtils.hasText(sortBy)) {
			Direction direction = StringUtils.hasText(sortDirection)
					&& SORT_DIRECTION_ASC.equalsIgnoreCase(sortDirection) ? Direction.ASC : Direction.DESC;
			return PageRequest.of(pageNumber, pageSize, Sort.by(new Order(direction, sortBy)));
		}
		return PageRequest.of(pageNumber, pageSize);
	}

	@GetMapping(path = "/async/", produces = MediaType.APPLICATION_JSON_VALUE)
	public void run(String... args) throws Exception {
		long start = System.currentTimeMillis();
		logger.info("Elapsed time: start " + (System.currentTimeMillis() - start));
		CompletableFuture<ProjectDetailModel> testCase1 = asynchronousService.findUser("PivotalSoftware");
		CompletableFuture<ProjectDetailModel> testCase2 = asynchronousService.findUser("CloudFoundry");
		CompletableFuture<ProjectDetailModel> testCase3 = asynchronousService.findUser("Spring-Projects");
		CompletableFuture<ProjectDetailModel> testCase4 = asynchronousService.findUser("PivotalSoftware");
		CompletableFuture<ProjectDetailModel> testCase5 = asynchronousService.findUser("CloudFoundry");
		CompletableFuture<ProjectDetailModel> testCase6 = asynchronousService.findUser("Spring-Projects");

		// Wait until they are all done
		CompletableFuture.allOf(testCase1, testCase2, testCase3, testCase4, testCase5, testCase6).join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + testCase1.get());
		logger.info("--> " + testCase2.get());
		logger.info("--> " + testCase3.get());
		logger.info("--> " + testCase4.get());
		logger.info("--> " + testCase5.get());
		logger.info("--> " + testCase6.get());
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProjectDetailModel> createProject(@RequestBody ProjectDetailModel projectDetailModel, UriComponentsBuilder ucb) {
		ProjectDetailModel create = pMSService.createProject(projectDetailModel);
		return ResponseEntity.created(ucb
									  .path(REQUEST_PARAM_MAPPING_PROJECT + "/{id}")
									  .buildAndExpand(create.getId())
									  .toUri())
							  .body(create);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProjectDetailModel> updateProject(@PathVariable Long id, @RequestBody ProjectDetailModel projectDetailModel) {
		projectDetailModel.setId(id);
		ProjectDetailModel update = pMSService.updateProject(projectDetailModel);
		return ResponseEntity.ok(update);
	}

	@DeleteMapping(path = "/{id}")
	ResponseEntity<Void> deleteProject(@PathVariable Long id) {
		pMSService.deleteProject(id);
		return ResponseEntity.ok(null);
	}
}
