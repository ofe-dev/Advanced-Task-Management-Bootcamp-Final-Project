package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.UpdateProjectRequestModel;
import com.omerfarukerol.models.UpdateProjectResponseModel;
import com.omerfarukerol.models.TaskDetailResponseModel;
import com.omerfarukerol.models.GetProjectTasksRequest;
import com.omerfarukerol.models.GetTeamTasksRequest;
import com.omerfarukerol.models.GetMemberTasksRequest;

import java.util.List;

public interface IProjectService {
    CreateProjectResponseModel createProject(CreateProjectRequestModel requestModel);
    UpdateProjectResponseModel updateProject(UpdateProjectRequestModel requestModel);
    List<TaskDetailResponseModel> getProjectTasks(GetProjectTasksRequest requestModel);
    List<TaskDetailResponseModel> getTeamTasks(GetTeamTasksRequest requestModel);
    List<TaskDetailResponseModel> getMemberTasks(GetMemberTasksRequest requestModel);
}
