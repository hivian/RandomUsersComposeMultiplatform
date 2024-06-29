import Foundation
import UIKit
import GoogleMaps
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {

        KoinCommonKt.doInitKoin()
        GMSServices.provideAPIKey("AIzaSyApee_Aqqchb9MMyebMkS04utfLmUHPI4k")
        return true
    }
}
