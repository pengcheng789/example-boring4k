/*
 * Copyright (c) 2018 Cai Pengcheng
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package top.pengcheng789.kotlin.example.boring4k.controller

import top.pengcheng789.java.boring.annotation.Action
import top.pengcheng789.java.boring.annotation.Controller
import top.pengcheng789.java.boring.annotation.Inject
import top.pengcheng789.java.boring.bean.Data
import top.pengcheng789.java.boring.bean.Param
import top.pengcheng789.java.boring.bean.View
import top.pengcheng789.kotlin.example.boring4k.service.GreetingService

/**
 * @author Cai Pengcheng
 */
@Controller
class HomeController {

    /**
     * Show the usage of dependency injection.
     */
    @Inject
    private lateinit var service: GreetingService

    /**
     * Show the usage of 'Data Controller'.
     * You can apply the controller to REST framework.
     */
    @Action(method = Action.RequestMethod.GET, path = "/")
    fun index(): Data<String> {
        // Create a 'Data' object.
        // You can create the 'Data' object of lots of type by generic,
        // and if not a 'String' type data, it will be resolved a json string.
        val data: Data<String> = Data()

        // set up the data by the model.
        data.model = service.getGreeting()

        return data
    }

    /**
     * Show the usage of 'View Controller' without request parameters.
     * This is a traditional controller that response data via html.
     */
    @Action(method = Action.RequestMethod.GET, path = "/home")
    fun home(): View {
        // Return a 'View' object (You should specify a name of html file).
        // The default template path is '/WEB-INF/templates/'.
        // So the html file path is '/WEB-INF/templates/home.html'
        // if you specify "home" as html file name.
        return View("home")
    }

    /**
     * Show the usage of 'View Controller' with request parameter
     * (of course, you can also declare a parameter in 'Data Controller').
     *
     * Note: Both of type of controllers declare a parameters just only one
     * and must be one, and the type of parameter must be 'top.pengcheng.java.bean.Param'.
     *
     * Once you declare the parameter, the servlet will return '400' status if request
     * don't have parameter.
     */
    @Action(method = Action.RequestMethod.GET, path = "/greeting")
    fun greeting(param: Param): View {
        val view = View("greeting")

        // Get the data from 'Service'.
        val greeting = service.getGreeting(param.getString("name"))

        // Transfer a value to the template.
        view.setAttribute("greeting", greeting)

        return view
    }
}
