package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;

public interface IProjectService {
    CreateProjectResponseModel createProject(CreateProjectRequestModel requestModel);
}
