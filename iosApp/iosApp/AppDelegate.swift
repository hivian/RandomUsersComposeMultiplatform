import Foundation
import UIKit
import GoogleMap
import ComposeApp

@UIApplicationMain
class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {

        GMSServices.provideAPIKey("")
        return true
    }
}
