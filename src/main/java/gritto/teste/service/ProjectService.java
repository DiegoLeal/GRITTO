package gritto.teste.service;

import gritto.teste.exceptions.ProjectIdException;
import gritto.teste.exceptions.ProjectNotFoundException;
import gritto.teste.model.Backlog;
import gritto.teste.model.Project;
import gritto.teste.model.Usuario;
import gritto.teste.repository.BacklogRepository;
import gritto.teste.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private BacklogRepository backlogRepository;

  @Autowired
  private UsuarioService usuarioService;

  public Project saveOrUpdateProject(Project project, String email) {

    if (project.getId() != null) {
      Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
      if (existingProject != null && (!existingProject.getProjectLeader().equals(email))) {
        throw new ProjectNotFoundException("Project not found in your account");
      } else if (existingProject == null) {
        throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier() + "' cannot be updated because it doesn't exist");
      }
    }

    try {

      Usuario usuario = usuarioService.findByEmail(email);


      project.setUser(usuario);
      project.setProjectLeader(usuario.getEmail());
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

      if (project.getId() == null) {
        Backlog backlog = new Backlog();
        project.setBacklog(backlog);
        backlog.setProject(project);
        backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      }

      if (project.getId() != null) {
        project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
      }

      return projectRepository.save(project);

    } catch (Exception e) {
      throw new ProjectIdException(
              "Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
    }

  }


  public Project findProjectByIdentifier(String projectId, String email) {

    //Only want to return the project if the user looking for it is the owner
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

    if (project == null) {
      throw new ProjectIdException("Project ID '" + projectId + "' does not exist");
    }

    if (!project.getProjectLeader().equals(email)) {
      throw new ProjectNotFoundException("Project not found in your account");
    }

    return project;
  }

  public Iterable<Project> findAllProjects(String email) {
    return projectRepository.findAllByProjectLeader(email);
  }


  public void deleteProjectByIdentifier(String projectid, String email) {
    projectRepository.delete(findProjectByIdentifier(projectid, email));
  }

}
