package com.tales.myannotations.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tales.myannotations.domain.User;
import com.tales.myannotations.dto.UserDTO;
import com.tales.myannotations.enums.Perfil;
import com.tales.myannotations.repositories.UserRepository;
import com.tales.myannotations.security.UserSS;
import com.tales.myannotations.services.exception.AuthorizationException;
import com.tales.myannotations.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3service;

	@Autowired
	private ImageService imageService;	
	
	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
private Integer size;

	public User find(Integer id) {

		UserSS userss = UserSSService.authenticated();
		if (userss == null || !id.equals(userss.getId()) && !userss.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("User not Autorizated");
		}

		User obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("User not Found");
		}
		return obj;

	}

	public List<User> findAll() {
		UserSS userss = UserSSService.authenticated();
		if (userss == null || !userss.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("User not Autorizated");
		}

		return repo.findAll();
	}

	public User insert(User obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;

	}

	public void update(User obj) {
		UserSS userss = UserSSService.authenticated();
		if (userss == null || !obj.getId().equals(userss.getId()) && !userss.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("User not Autorizated");
		}
		repo.findOne(obj.getId());
		try {

			if (obj.getName() != null) {
				repo.updatename(obj.getId(), obj.getName());
			}
			;
			if (obj.getCpf() != null) {
				repo.updatecpf(obj.getId(), obj.getCpf());
			}
			if (obj.getEmail() != null) {
				repo.updateemail(obj.getId(), obj.getEmail());
			}
			if (obj.getPassword() != null) {
				repo.updatepassword(obj.getId(), pe.encode(obj.getPassword()));
			}
			System.out.println(obj.getNotes());
			if (!obj.getNotes().isEmpty()) {
				repo.save(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException("Not Permitted");
		}
	}

	public void delete(Integer id) {
		UserSS userss = UserSSService.authenticated();
		if (userss == null || !id.equals(userss.getId()) && !userss.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("User not Autorizated");
		}
		find(id);
		repo.delete(id);

	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getCpf(), objDto.getEmail(),
				pe.encode(objDto.getPassword()));
	}

	public URI uploadProfilePicture(MultipartFile multipartfile) {
		UserSS userss = UserSSService.authenticated();
		if (userss == null) {
			throw new AuthorizationException("Not permited");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartfile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + userss.getId() + ".jpg";
		URI uri = s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		User u = repo.findOne(userss.getId());
		u.setImageUrl(uri.toString());
		repo.save(u);
		return uri;
	}

}
