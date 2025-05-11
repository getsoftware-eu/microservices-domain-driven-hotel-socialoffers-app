package eu.getsoftware.hotelico.service.booking.adapter.`in`.web.controller.customer
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.servlet.mvc.support.RedirectAttributes

abstract class AbstractCustomerController<IAsset, IAssetFormDTO> {

    fun doList(
        model: Model 
        // ,@AuthenticationPrincipal AppUserPrincipal principal 
    ) {
        model.addAttribute("canEdit", false);
    }

    fun doSave(@ModelAttribute("asset") assetDTO: IAssetFormDTO,
               bindingResult: BindingResult,
               redirectAttrs: RedirectAttributes,
//               @AuthenticationPrincipal principal: AppUserPrincipal
    ): IAsset? {
//        if (bindingResult.hasErrors()) {
//            // form validation error
//            logger.debug("error ${bindingResult.errorCount}")
//            redirectAttrs.addFlashAttribute("error", messageSource.getMessage("common.error", null, Locale.GERMAN))
//            return "redirect:/$PATH_ITEM_CREATE"
//        }
            
//        val asset = assetSaService.save(assetDTO)
//        setUserMessage("asset.save", redirectAttrs)

        return null
    }

    fun doUpdate(@ModelAttribute assetDTO: IAssetFormDTO,
                 @PathVariable("id") id: Long,
                 bindingResult: BindingResult,
                 redirectAttrs: RedirectAttributes
    ) {
//        if (bindingResult.hasErrors()) {
//            // form validation error
//            logger.debug("error ${bindingResult.errorCount}")
//            redirectAttrs.addFlashAttribute("error", messageSource.getMessage("common.error", null, Locale.GERMAN))
//            return "redirect:/$PATH_ITEM_EDIT/${id}"
//        }

//        assetSaService.updateFromForm(id, assetDTO)
//        setUserMessage("asset.update", redirectAttrs)
    }
}
