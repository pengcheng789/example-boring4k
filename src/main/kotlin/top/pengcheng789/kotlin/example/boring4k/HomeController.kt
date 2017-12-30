package top.pengcheng789.kotlin.example.boring4k

import top.pengcheng789.java.boring.annotation.Action
import top.pengcheng789.java.boring.annotation.Controller
import top.pengcheng789.java.boring.bean.Data
import top.pengcheng789.java.boring.bean.View

/**
 * @author pen
 */
@Controller
class HomeController {

    @Action(method = Action.RequestMethod.GET, path = "/")
    fun index(): Data<String> {
        val data: Data<String> = Data()
        data.model = "Hello World!"

        return data
    }

    @Action(method = Action.RequestMethod.GET, path = "/home")
    fun home(): View {
        return View("home")
    }
}
