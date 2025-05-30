// path: hotelico/service/booking/adapter/in/web/controller/customer/AbstractCustomerController.java

package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller.customer;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractCustomerController<IAsset, IAssetFormDTO> {

    public void doList(Model model /* , AppUserPrincipal principal */) {
        model.addAttribute("canEdit", false);
    }

    public IAsset doSave(
            @ModelAttribute("asset") IAssetFormDTO assetDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs
            /* , AppUserPrincipal principal */
    ) {
        // if (bindingResult.hasErrors()) {
        //     redirectAttrs.addFlashAttribute("error", "Fehler beim Speichern");
        //     return "redirect:/path-to-create"; // заменить на актуальный путь
        // }

        // IAsset asset = assetSaService.save(assetDTO);
        // setUserMessage("asset.save", redirectAttrs);

        return null;
    }

    public void doUpdate(
            @ModelAttribute IAssetFormDTO assetDTO,
            @PathVariable("id") Long id,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs
    ) {
        // if (bindingResult.hasErrors()) {
        //     redirectAttrs.addFlashAttribute("error", "Fehler beim Aktualisieren");
        //     return "redirect:/path-to-edit/" + id;
        // }

        // assetSaService.updateFromForm(id, assetDTO);
        // setUserMessage("asset.update", redirectAttrs);
    }
}
