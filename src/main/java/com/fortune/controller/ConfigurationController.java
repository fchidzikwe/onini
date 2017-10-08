package com.fortune.controller;

import com.fortune.model.*;
import com.fortune.service.*;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by fortune on 7/25/17.
 */

@Controller
public class ConfigurationController {
  @Autowired
  CountryService countryService;

  @Autowired
  CityService cityService;

  @Value("${app.user.root}")
  private String userRoot;

  @Autowired
  TariffUnitService  tariffUnitService;

  @Autowired
  NationalityService nationalityService;

  @Autowired
  CostsService costsService;

  @Autowired
  PostingGroupService postingGroupService;

  @Autowired
  TariffService tariffService;

  @Autowired
  UserService userService;

  @Autowired
  FacilityConfigurationService facilityConfigurationService;

  @Autowired
  MatterService  matterService;

  @RequestMapping(value = "/countryform", method = RequestMethod.GET)
  public String getCoutryForm(Model model){
    List<Country> countryList = countryService.findAll();
    model.addAttribute("country", new Country());
    model.addAttribute("countryList",countryList);
    return "country";
  }

  @RequestMapping(value = "/addcountry", method = RequestMethod.POST)
  public String registerCountry(RedirectAttributes  redirectAttributes,@Valid Country country, BindingResult bindingResult, Model  model){
    countryService.saveCountry(country);
    redirectAttributes.addFlashAttribute("successMessage", country.getName()  +" Registred");
    return "redirect:/countryform";
  }

  @RequestMapping(value = "/gettarrifunitform", method = RequestMethod.GET)
  public String getTarriffUnitForm(Model model){
    List<Country> countryList = countryService.findAll();
    model.addAttribute("tarriffunit", new TarrifUnit());
    return "tarriffunit";
  }

  @RequestMapping(value = "/addtarrifunit", method = RequestMethod.POST)
  public String registerTarrifUnit(RedirectAttributes redirectAttributes,@Valid TarrifUnit tarrifUnit, BindingResult bindingResult, Model  model){
    tariffUnitService.saveTariffUnit(tarrifUnit);
    redirectAttributes.addAttribute("successMessage", tarrifUnit.getName()  +" Registred");
    return "redirect:/gettarrifunitform";
  }

  @RequestMapping(value = "/cityform", method = RequestMethod.GET)
  public String getCityForm(Model model){
    List<Country> countryList = countryService.findAll();
    model.addAttribute("city", new City());
    model.addAttribute("countryList", countryList);
    return "city";
  }

  @RequestMapping(value = "/addcity", method = RequestMethod.POST)
  public String addCity(RedirectAttributes redirectAttributes,@Valid City city, BindingResult bindingResult, Model model){
    cityService.saveCity(city);
    redirectAttributes.addFlashAttribute("successMessage", "");
    return  "redirect:/cityform";
  }

  @RequestMapping(value = "/getnationalityform", method = RequestMethod.GET)
  public String getNationalityForm(Model model){
    List<Nationality> nationalityList = nationalityService.findAll();
    model.addAttribute("nationalityObj", new Nationality());
    model.addAttribute("countryList",nationalityList);
    return "nationality";
  }


  @RequestMapping(value = "/addnationality", method = RequestMethod.POST)
  public String registerNationality(RedirectAttributes redirectAttributes,@Valid Nationality nationality, BindingResult bindingResult, Model  model){
    nationalityService.saveNationality(nationality);
    redirectAttributes.addFlashAttribute("successMessage", nationality.getName()  +" Registred");
    return "redirect:/getnationalityform";
  }



  @RequestMapping(value = "/getpostinggroupform", method = RequestMethod.GET)
  public String getPostingGroupForm(Model model){
    List<PostingGroup> postingGroupList = postingGroupService.findAllPostingGroups();
    model.addAttribute("postingGroup", new PostingGroup());
    model.addAttribute("postingGroupList",postingGroupList);
    return "postinggroup";
  }


  @RequestMapping(value = "/addpostinggroup", method = RequestMethod.POST)
  public String registerPostingGroup(@Valid PostingGroup postingGroup, BindingResult bindingResult, Model  model, RedirectAttributes redirectAttributes){
    postingGroupService.savePostingGroup(postingGroup);
    redirectAttributes.addFlashAttribute("successMessage", postingGroup.getGroup() +" Registred");
    return "redirect:/getpostinggroupform";
  }

  @RequestMapping(value = "/gettarifform", method = RequestMethod.GET)
  public String getTariffForm(Model model){
    List<PostingGroup> postingGroupList = postingGroupService.findAllPostingGroups();
    List<TarrifUnit> tarrifUnitList = tariffUnitService.findAllTariffUnits();
    model.addAttribute("postingGroupList", postingGroupList);
    model.addAttribute("tarrifUnitList", tarrifUnitList);
    model.addAttribute("tariff", new Tariff());
    return "tarriff";
  }

  @RequestMapping(value = "/getmatterform", method = RequestMethod.GET)
  public String getMatterForm(Model model){
    List<Matter> matterList = matterService.findAllMatters();
    model.addAttribute("matter", new Matter());
    model.addAttribute("matterList",matterList);
    return "matter";
  }

  @RequestMapping(value = "/addmatter", method = RequestMethod.POST)
  public String registerMatter(@Valid Matter matter, BindingResult bindingResult, Model  model, RedirectAttributes redirectAttributes){
    matterService.save(matter);
    redirectAttributes.addFlashAttribute("successMessage", matter.getName() +" Registred");
    return "redirect:/getmatterform";
  }


    @RequestMapping(value = "/getcostsform", method = RequestMethod.GET)
    public String getCostsForm(Model model){
        List<Costs> costsList = costsService.findAllCosts();
        model.addAttribute("costs", new Costs());
        model.addAttribute("costsList",costsList);
        return "costs";
    }

    @RequestMapping(value = "/addcosts", method = RequestMethod.POST)
    public String registerCosts(@Valid Costs costs, BindingResult bindingResult, Model  model, RedirectAttributes redirectAttributes){
        costsService.save(costs);
        redirectAttributes.addFlashAttribute("successMessage", costs.getName() +" Registred");
        return "redirect:/getcostsform";
    }


  @RequestMapping(value = "/addtariff", method = RequestMethod.POST)
  public String addTariff(@Valid Tariff tariff, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
    tariffService.saveTariff(tariff);
    redirectAttributes.addFlashAttribute("successMessage", tariff.getDescription()+ " added");
    return  "redirect:/gettarifform";
  }



    @PostMapping("/saveconfig")
    public String saveConfig(@Valid FacilityConfiguration facilityConfiguration, BindingResult bindingResult, Model model){
        List<FacilityConfiguration> facilityConfigurationList = facilityConfigurationService.findAllFacilities().orElse(null);
        if(facilityConfigurationList.isEmpty()){
            facilityConfigurationService.saveFacility(facilityConfiguration);
        }
        else{
            FacilityConfiguration facilityConfiguration1 = facilityConfigurationList.get(0);
            facilityConfiguration1.setEmail(facilityConfiguration.getEmail());
            facilityConfiguration1.setName(facilityConfiguration.getName());
            facilityConfiguration1.setWebsite(facilityConfiguration.getWebsite());

            facilityConfigurationService.saveFacility(facilityConfiguration1);
        }
        model.addAttribute("successMessage", facilityConfiguration.getName() +" saved!");
        return "redirect:/settings";
    }

    @GetMapping("/settings")
    public String setttingsPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        List<FacilityConfiguration> facilityConfigurationList = facilityConfigurationService.findAllFacilities().orElse(null);
        if(facilityConfigurationList.size()>0){
            model.addAttribute("facilityConfiguration", facilityConfigurationList.get(0));
        }
        else
        {
            model.addAttribute("facilityConfiguration", new FacilityConfiguration());
        }
        List<FacilityConfiguration> facilityConfigurations = facilityConfigurationService.findAllFacilities().orElse(null);
        if(facilityConfigurations.size()>0){
            FacilityConfiguration facilityConfiguration = facilityConfigurations.get(0);
            model.addAttribute("facilityConfigurations", facilityConfiguration);
        }else {
            model.addAttribute("facilityConfigurations", null);
        }
        model.addAttribute("user", user);
        return "viewconfig";
    }

    @GetMapping("/config")
    public String configPage(Model model){
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        List<FacilityConfiguration> facilityConfigurationList = facilityConfigurationService.findAllFacilities().orElse(null);
        if(facilityConfigurationList.size()>0){
            model.addAttribute("facilityConfiguration", facilityConfigurationList.get(0));
        }
        else
        {
            model.addAttribute("facilityConfiguration", new FacilityConfiguration());
        }
        List<FacilityConfiguration> facilityConfigurations = facilityConfigurationService.findAllFacilities().orElse(null);
        if(facilityConfigurations.size()>0){
            FacilityConfiguration facilityConfiguration = facilityConfigurations.get(0);
            model.addAttribute("facilityConfigurations", facilityConfiguration);
        }else {
            model.addAttribute("facilityConfigurations", null);
        }
        model.addAttribute("user", user);
        return "config";
    }


    @RequestMapping(value = "/user/upload", method = RequestMethod.POST)
  public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
    String fileName = "profilepic.jpg";
    List<FacilityConfiguration> facilityConfigurationList = facilityConfigurationService.findAllFacilities().orElse(null);
    if(!facilityConfigurationList.isEmpty()) {
      FacilityConfiguration facilityConfiguration = facilityConfigurationList.get(0);
      if (!file.isEmpty()) {
        try {
          String saveDirectory = userRoot + File.separator + facilityConfiguration.getId() + File.separator;
          File test = new File(saveDirectory);
          if (!test.exists()) {
            test.mkdirs();
          }
          byte[] bytes = file.getBytes();
          ByteArrayInputStream imageInputStream = new ByteArrayInputStream(bytes);
          BufferedImage image = ImageIO.read(imageInputStream);
          BufferedImage thumbnail = Scalr.resize(image, 200);
          BufferedImage thumbnailAvatar = Scalr.resize(image, 100);
          File thumbnailOut = new File(saveDirectory + fileName);
          ImageIO.write(thumbnail, "png", thumbnailOut);
          ImageIO.write(thumbnailAvatar, "png", thumbnailOut);
          facilityConfigurationService.updateProfilePicture(facilityConfiguration, fileName);
          System.out.println("Image Saved::: " + fileName);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if(facilityConfigurationList.size()>0){
        model.addAttribute("facilityConfiguration", facilityConfigurationList.get(0));
      }
      else
      {
        model.addAttribute("facilityConfiguration", new FacilityConfiguration());
      }
      return "config"; //return view page
    }else{
      if(facilityConfigurationList.size()>0){
        model.addAttribute("facilityConfiguration", facilityConfigurationList.get(0));
      }
      else
      {
        model.addAttribute("facilityConfiguration", new FacilityConfiguration());
      }
      return  "config"; ///return config edit
    }
  }

  @RequestMapping(value="/user/profile-picture", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] profilePicture() throws IOException {
    List<FacilityConfiguration> facilityConfigurationList = facilityConfigurationService.findAllFacilities().get();
    if(!facilityConfigurationList.isEmpty()) {
      FacilityConfiguration facilityConfiguration =  facilityConfigurationList.get(0);
      String profilePicture = userRoot + File.separator + facilityConfiguration.getId() + File.separator + facilityConfiguration.getProfilePicture();
      if(new File(profilePicture).exists()) {
        return IOUtils.toByteArray(new FileInputStream(profilePicture));
      } else {
        return null;
      }
    }
    else{
      return null;
    }
  }

  @ModelAttribute("user")
  public User user(){
    return new User();
  }
}
