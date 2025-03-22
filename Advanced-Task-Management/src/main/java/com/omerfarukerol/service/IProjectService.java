package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.UpdateProjectRequestModel;
import com.omerfarukerol.models.UpdateProjectResponseModel;

public interface IProjectService {
    CreateProjectResponseModel createProject(CreateProjectRequestModel requestModel);
    UpdateProjectResponseModel updateProject(UpdateProjectRequestModel requestModel);
}
