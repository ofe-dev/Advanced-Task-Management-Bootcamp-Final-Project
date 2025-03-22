package com.omerfarukerol.controller;

import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.UpdateProjectRequestModel;
import com.omerfarukerol.models.UpdateProjectResponseModel;
import com.omerfarukerol.models.RootResponse;

public interface IProjectController {
    RootResponse<CreateProjectResponseModel> createProject(CreateProjectRequestModel request);
    RootResponse<UpdateProjectResponseModel> updateProject(UpdateProjectRequestModel request);
} 